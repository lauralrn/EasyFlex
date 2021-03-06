## Validating Flexibility Commitments with Hedera Hashgraph 
The  objective  of  this  project  was  to  create  a  proof  of  concept  of  using  distributed  ledger  technology  -specifically, Hedera Hashgraph - to validate flexibility commitments from assets.
To know more: https://docs.google.com/presentation/d/1bwn3KQCNz5rwXfNay_RLen6CBqWYY6DiAl5RqHJTOQQ/edit?usp=sharing



## Requirements 
A .env file must be created with accounts and mirror node information.
To create an account, you need to create an Hedera Portal profile registering on https://portal.hedera.com/register.

Once you've completed setting up your profile, your home page will automatically create a testnet account and generate the public and private key pair associated with it. You can easily copy your accountId, public key, and private key information to your clipboard to use when configuring your SDK environment for testnet or set up the .env file. 

An .env file for this project should look like the text below.

```bash
# Account keys
OPERATOR_ID=0.0.1752
OPERATOR_KEY=302e020100300506032b65700422042068b0ecae635505d8t345674e4f7d8b5b52682e419068acd225
OPERATOR_PUBLIC_KEY=302a300506032b65700323457bb8c7b831d0d91d163b2ad791e75064f6015b2f5a621c2aea3eaec
MIRROR_NODE_ADDRESS=api.testnet.kabuto.sh:50211
NODE_ADDRESS=0.0.3
```

## SDK and API Reference
Hedera Hashgraph SDK with examples: https://github.com/hashgraph/hedera-sdk-java

Hedera Hashgraph API: https://docs.hedera.com/guides/docs/hedera-api/consensus-service

## How to use
Run Main.java and it will guide you trough the different services.

Two files are used for the flexibility validation: one file that will result in an invalid commitment (invalid file) and one in a valid commitment (valid file). If the file selected is the invalid one, a string will be printed sayinging the commitment is not valid.

The folder example, contains runnable examples using Hedera Hasgraph.

Below, a gif showing how the program looks like.

![Alt Text](https://media.giphy.com/media/ZY325sL6acFW3U3ic3/giphy.gif)

## Roadmap
The idea is expanding the use of Hedera Hasgraph, creating a mainnet and using our own mirror and build a proper application to retrieve the flexibility resurces' power and energy information to display them and validate the flexibility commitment. This application would be availabe for all the users.

## Support
Laura Laringe: lauralaringe@gmail.com 

Oscar Damanik: oadamanik@gmail.com

## Authors
Ainhoa Sanchez, Emilia Chojkiewicz, Jorge Ballesteros, Hoda Ataee, Laura Laringe, Oscar Damanik
