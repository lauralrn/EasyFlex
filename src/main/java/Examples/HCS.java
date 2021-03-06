package Examples;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaStatusException;
import com.hedera.hashgraph.sdk.TransactionId;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.consensus.ConsensusMessageSubmitTransaction;
import com.hedera.hashgraph.sdk.consensus.ConsensusTopicCreateTransaction;
import com.hedera.hashgraph.sdk.consensus.ConsensusTopicId;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;
import com.hedera.hashgraph.sdk.mirror.MirrorClient;
import com.hedera.hashgraph.sdk.mirror.MirrorConsensusTopicQuery;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class HCS {

    // Grab the OPERATOR_ID and OPERATOR_KEY from the .env file
    private static final AccountId OPERATOR_ID = AccountId.fromString(Objects.requireNonNull(Dotenv.load().get("OPERATOR_ID")));

    private static final Ed25519PrivateKey OPERATOR_KEY = Ed25519PrivateKey.fromString(Objects.requireNonNull(Dotenv.load().get("OPERATOR_KEY")));

    // Grab the mirror node endpoint from the .env file
    private static final String MIRROR_NODE_ADDRESS = Objects.requireNonNull(Dotenv.load().get("MIRROR_NODE_ADDRESS"));

    private HCS() { }

    public static void main(String[] args) {

        // Build Hedera testnet client
        Client client = Client.forTestnet();

        // Set the operator account ID and operator private key
        client.setOperator(OPERATOR_ID, OPERATOR_KEY);

        // Build the mirror node client
        final MirrorClient mirrorClient = new MirrorClient(MIRROR_NODE_ADDRESS);


        //Create a new topic
        final TransactionId transactionId;
        try {
            transactionId = new ConsensusTopicCreateTransaction()
                    .execute(client);

            //Grab the newly generated topic ID
            final ConsensusTopicId topicId = transactionId.getReceipt(client).getConsensusTopicId();

            System.out.println("Your topic ID is: " +topicId);

            //Subscribe to the topic TopicId
        new MirrorConsensusTopicQuery()
                .setTopicId(topicId)
                .subscribe(mirrorClient, resp -> {
                            String messageAsString = new String(resp.message, StandardCharsets.UTF_8);

                            System.out.println(resp.consensusTimestamp + " received topic message: " + messageAsString);
                        },
                        // On gRPC error, print the stack trace
                        Throwable::printStackTrace);

            //Submit a message to a topic
            new ConsensusMessageSubmitTransaction()
                    .setTopicId(topicId)
                    .setMessage("hello")
                    .execute(client)
                    .getReceipt(client);

            Thread.sleep(30000);

        } catch (HederaStatusException | InterruptedException e) {
            e.printStackTrace();
        }



    }
}



