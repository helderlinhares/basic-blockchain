# BASIC BLOCKCHAIN

## Description

This project is a basic example of a Blockchain using Kotlin.

In this, you will be able to:
* List the project Blockchain's
* Create a Wallet (KeyPair) to use in the project
* Open a Wallet using a Private Key to check the balance
* Mine the project's coin to add balance in the Wallet
* Send coins to other Wallets in the project

OBS: All the content will be stored only in the application memory and will be lost after restarting the application.

### Technical Info

* Technologies:
    * Kotlin
    * Spring Boot
    * Sleuth
    * Gradle

## Building from Source

To clean/build the project from the console you can use the command:

```console
    ./gradlew clean build
```

To run only tests from the console you can use the command:

```console
    ./gradlew test
```

OBS: You can also use your IDE to run Gradle tasks.

## Running the project

To run the project you can use the command:

```console
    ./gradlew bootRun
```

OBS: You can also use your IDE to run the Project.

## Endpoints

* **LISTING THE BLOCKCHAIN**
  * `GET` `/blockchain`
  * **Description**: This endpoint returns the complete list of blocks in the chain
  * **Curl Example**:
  ```bash
  curl --location --request GET 'http://localhost:9093/blockchain'
  ```
  * **Response Example**:
  ```json
  [
    {
        "previousId": 0,
        "id": 1,
        "transaction": {
            "amount": 1,
            "senderPublicKey": "GENESIS",
            "receiverPublicKey": "helderlinhares"
        },
        "time": 1644687088177,
        "hash": "bd92fdaadc60eb9c9f6b02d244b1875a9048a2a3113ead93b1fd5796d2ef3772"
    }
  ]
  ```

* **CREATE A WALLET**
  * `GET` `/blockchain/wallet`
  * **Description**: This endpoint returns a new wallet with it`s balance, private and public keys (KeyPair in PEM String format)
  * **Curl Example**:
  ```bash
  curl --location --request GET 'http://localhost:9093/blockchain/wallet'
  ```
  * **Response Example**:
  ```json
  {
    "balance": 0,
    "privateKey": "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4zA8UtIl9mePVbzw2A/8NVfJtTRZQuK5dcUqYOkZndjZebgRAd5lB9mMrM99yQcTwF7oTxWc9a8K74KN0KCaGO49nPuO+d2TZ9JjHoWHeasB8qhEVQXBQaFxP9YDZ9qmEZM6VFzfWoELjgEvPE5HH8MtcEKHZWTbsKumSEv/oJ8Z6cLpLFpyKHWyf1oN5hJH2VtGbOKN5naYwMEwXaAogcmq4c1Ow4tO925FTltppJV/B2D9Ykg6kstc8Oct1+9qvfXKLbqvrnB03zlnyi2/kzBF0lLzBXjkVoF3awsdIuWnizjZqeBIO9GsOAL7bENPnEq/2+DciGtgAd2yvQUuXAgMBAAECggEAAznHzKnNxwOsiLlcbP16LwYRHzozbqZjd4YlwvPpP0d0k1eOOxNt5QfKERkETxdYBOLe0zIao/MDJde56JXrJgXLjUKvYFaegcK46f8p7U8bkdaQVzH8QO7ZauzWlwhBFRmrlLyZn0OY8JFQc/mpZRJb0mI98JVJo0X5pRXR3rzPaTEwaypW/2nnhVoUavSKHjuXn7LXZAklIdqrHVsLEqcmAY3XCi7Sxk1dj50CKKyWTBEy7VIAil2eTXSQhpKzypvq4CN8RW8UFxWmvwiu25Qb6HiytuN2MiB+HdZFfg30FomGnksHDL9PRlKa0uZZlHwMvN4Wi/jOH7u/YonEaQKBgQDZ0kZQrN1wfSTV0b3MYTrfaISg5c9JlPQScWZIMDbruP+2yOLZmG3ci+IBnR/PoUK7kHUdX1xDqHSXdFFQ1rgkI/EAXq/QELQYttWway9eyHMohNIQPYoJB8vQVc82rxFzr5/izZHeRrQ5eYc1cRuyYFK9E7L9BdWyJm6m4FwxDwKBgQDZL/kCN20hIPZmt5iEN88ztYlRFgEpMf0QIG0MBMQuxFOh4ciDXxcuZ+F1fOuEruDvIYv33OlomqaU+l1/Ufe6d9mu90r72a+DZ4MgZEn1et94ZFCjpPlPh0okffr53Q/OO8VkLwx/h5R3SykK9W8/2zjGu6cpjIkV1Lc/omks+QKBgDguzIQykktZfA7KsrHswkqtosf9fqH0EaBQL5mfFgtnBE4ZMqn9bxjb28eex32+kn/emtagqiOpZ6lJD1BSkdyAiMEXqzF3nXxibGu8udMw0MM6vxQBS4FYyXFLMM9iCJZWTl8ptt4mToYkmoNmUUaJIc4zLo+k2aHB1/ijFiRxAoGBAJliM+3Izx1ydhIc322hLIhMUOofLDq3vw1AmUK/qv2MUbQmDMIayfhQDP44duyBxQGdI4itT3o8niDgoF9muBnMwsO6ig5LspSO6iBaM5FWcbRIxFqODDSKS2ZyQmlsIdl4hPM39w+p5MRLJHVE2Ii0feh8ZpYFVltAmxopzTWhAoGBALlwONOKxm+jMNgxX32cC2Q3yGYf44fuzQQYSuzF/ROGse3K2bLV2T6+Y4lfKYY1BbkBtYB6kbayxWxbQpUk/9LojCS33D32/zO95G9MkAFVy59D+zo1jedqVM2Nlv8yGQk5s+02149I+TmJKQQX43f/btNR4Z9WsYh2yf4lQpea",
    "publicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuMwPFLSJfZnj1W88NgP/DVXybU0WULiuXXFKmDpGZ3Y2Xm4EQHeZQfZjKzPfckHE8Be6E8VnPWvCu+CjdCgmhjuPZz7jvndk2fSYx6Fh3mrAfKoRFUFwUGhcT/WA2faphGTOlRc31qBC44BLzxORx/DLXBCh2Vk27CrpkhL/6CfGenC6Sxacih1sn9aDeYSR9lbRmzijeZ2mMDBMF2gKIHJquHNTsOLTvduRU5baaSVfwdg/WJIOpLLXPDnLdfvar31yi26r65wdN85Z8otv5MwRdJS8wV45FaBd2sLHSLlp4s42angSDvRrDgC+2xDT5xKv9vg3IhrYAHdsr0FLlwIDAQAB"
  }
  ```

* **OPEN A WALLET**
  * `GET` `/blockchain/wallet`
    * Headers:
      * `private-key`: Private Key from the wallet you want to see 
  * Description: This endpoint returns a valid wallet with it`s balance, private and public keys (KeyPair in PEM String format)
  * Curl Example:
  ```bash
  curl --location --request GET 'http://localhost:9093/blockchain/wallet/open' --header 'private-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4zA8UtIl9mePVbzw2A/8NVfJtTRZQuK5dcUqYOkZndjZebgRAd5lB9mMrM99yQcTwF7oTxWc9a8K74KN0KCaGO49nPuO+d2TZ9JjHoWHeasB8qhEVQXBQaFxP9YDZ9qmEZM6VFzfWoELjgEvPE5HH8MtcEKHZWTbsKumSEv/oJ8Z6cLpLFpyKHWyf1oN5hJH2VtGbOKN5naYwMEwXaAogcmq4c1Ow4tO925FTltppJV/B2D9Ykg6kstc8Oct1+9qvfXKLbqvrnB03zlnyi2/kzBF0lLzBXjkVoF3awsdIuWnizjZqeBIO9GsOAL7bENPnEq/2+DciGtgAd2yvQUuXAgMBAAECggEAAznHzKnNxwOsiLlcbP16LwYRHzozbqZjd4YlwvPpP0d0k1eOOxNt5QfKERkETxdYBOLe0zIao/MDJde56JXrJgXLjUKvYFaegcK46f8p7U8bkdaQVzH8QO7ZauzWlwhBFRmrlLyZn0OY8JFQc/mpZRJb0mI98JVJo0X5pRXR3rzPaTEwaypW/2nnhVoUavSKHjuXn7LXZAklIdqrHVsLEqcmAY3XCi7Sxk1dj50CKKyWTBEy7VIAil2eTXSQhpKzypvq4CN8RW8UFxWmvwiu25Qb6HiytuN2MiB+HdZFfg30FomGnksHDL9PRlKa0uZZlHwMvN4Wi/jOH7u/YonEaQKBgQDZ0kZQrN1wfSTV0b3MYTrfaISg5c9JlPQScWZIMDbruP+2yOLZmG3ci+IBnR/PoUK7kHUdX1xDqHSXdFFQ1rgkI/EAXq/QELQYttWway9eyHMohNIQPYoJB8vQVc82rxFzr5/izZHeRrQ5eYc1cRuyYFK9E7L9BdWyJm6m4FwxDwKBgQDZL/kCN20hIPZmt5iEN88ztYlRFgEpMf0QIG0MBMQuxFOh4ciDXxcuZ+F1fOuEruDvIYv33OlomqaU+l1/Ufe6d9mu90r72a+DZ4MgZEn1et94ZFCjpPlPh0okffr53Q/OO8VkLwx/h5R3SykK9W8/2zjGu6cpjIkV1Lc/omks+QKBgDguzIQykktZfA7KsrHswkqtosf9fqH0EaBQL5mfFgtnBE4ZMqn9bxjb28eex32+kn/emtagqiOpZ6lJD1BSkdyAiMEXqzF3nXxibGu8udMw0MM6vxQBS4FYyXFLMM9iCJZWTl8ptt4mToYkmoNmUUaJIc4zLo+k2aHB1/ijFiRxAoGBAJliM+3Izx1ydhIc322hLIhMUOofLDq3vw1AmUK/qv2MUbQmDMIayfhQDP44duyBxQGdI4itT3o8niDgoF9muBnMwsO6ig5LspSO6iBaM5FWcbRIxFqODDSKS2ZyQmlsIdl4hPM39w+p5MRLJHVE2Ii0feh8ZpYFVltAmxopzTWhAoGBALlwONOKxm+jMNgxX32cC2Q3yGYf44fuzQQYSuzF/ROGse3K2bLV2T6+Y4lfKYY1BbkBtYB6kbayxWxbQpUk/9LojCS33D32/zO95G9MkAFVy59D+zo1jedqVM2Nlv8yGQk5s+02149I+TmJKQQX43f/btNR4Z9WsYh2yf4lQpea'
  ```
  * **Response Example**:
  ```json
  {
    "balance": 0,
    "privateKey": "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4zA8UtIl9mePVbzw2A/8NVfJtTRZQuK5dcUqYOkZndjZebgRAd5lB9mMrM99yQcTwF7oTxWc9a8K74KN0KCaGO49nPuO+d2TZ9JjHoWHeasB8qhEVQXBQaFxP9YDZ9qmEZM6VFzfWoELjgEvPE5HH8MtcEKHZWTbsKumSEv/oJ8Z6cLpLFpyKHWyf1oN5hJH2VtGbOKN5naYwMEwXaAogcmq4c1Ow4tO925FTltppJV/B2D9Ykg6kstc8Oct1+9qvfXKLbqvrnB03zlnyi2/kzBF0lLzBXjkVoF3awsdIuWnizjZqeBIO9GsOAL7bENPnEq/2+DciGtgAd2yvQUuXAgMBAAECggEAAznHzKnNxwOsiLlcbP16LwYRHzozbqZjd4YlwvPpP0d0k1eOOxNt5QfKERkETxdYBOLe0zIao/MDJde56JXrJgXLjUKvYFaegcK46f8p7U8bkdaQVzH8QO7ZauzWlwhBFRmrlLyZn0OY8JFQc/mpZRJb0mI98JVJo0X5pRXR3rzPaTEwaypW/2nnhVoUavSKHjuXn7LXZAklIdqrHVsLEqcmAY3XCi7Sxk1dj50CKKyWTBEy7VIAil2eTXSQhpKzypvq4CN8RW8UFxWmvwiu25Qb6HiytuN2MiB+HdZFfg30FomGnksHDL9PRlKa0uZZlHwMvN4Wi/jOH7u/YonEaQKBgQDZ0kZQrN1wfSTV0b3MYTrfaISg5c9JlPQScWZIMDbruP+2yOLZmG3ci+IBnR/PoUK7kHUdX1xDqHSXdFFQ1rgkI/EAXq/QELQYttWway9eyHMohNIQPYoJB8vQVc82rxFzr5/izZHeRrQ5eYc1cRuyYFK9E7L9BdWyJm6m4FwxDwKBgQDZL/kCN20hIPZmt5iEN88ztYlRFgEpMf0QIG0MBMQuxFOh4ciDXxcuZ+F1fOuEruDvIYv33OlomqaU+l1/Ufe6d9mu90r72a+DZ4MgZEn1et94ZFCjpPlPh0okffr53Q/OO8VkLwx/h5R3SykK9W8/2zjGu6cpjIkV1Lc/omks+QKBgDguzIQykktZfA7KsrHswkqtosf9fqH0EaBQL5mfFgtnBE4ZMqn9bxjb28eex32+kn/emtagqiOpZ6lJD1BSkdyAiMEXqzF3nXxibGu8udMw0MM6vxQBS4FYyXFLMM9iCJZWTl8ptt4mToYkmoNmUUaJIc4zLo+k2aHB1/ijFiRxAoGBAJliM+3Izx1ydhIc322hLIhMUOofLDq3vw1AmUK/qv2MUbQmDMIayfhQDP44duyBxQGdI4itT3o8niDgoF9muBnMwsO6ig5LspSO6iBaM5FWcbRIxFqODDSKS2ZyQmlsIdl4hPM39w+p5MRLJHVE2Ii0feh8ZpYFVltAmxopzTWhAoGBALlwONOKxm+jMNgxX32cC2Q3yGYf44fuzQQYSuzF/ROGse3K2bLV2T6+Y4lfKYY1BbkBtYB6kbayxWxbQpUk/9LojCS33D32/zO95G9MkAFVy59D+zo1jedqVM2Nlv8yGQk5s+02149I+TmJKQQX43f/btNR4Z9WsYh2yf4lQpea",
    "publicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuMwPFLSJfZnj1W88NgP/DVXybU0WULiuXXFKmDpGZ3Y2Xm4EQHeZQfZjKzPfckHE8Be6E8VnPWvCu+CjdCgmhjuPZz7jvndk2fSYx6Fh3mrAfKoRFUFwUGhcT/WA2faphGTOlRc31qBC44BLzxORx/DLXBCh2Vk27CrpkhL/6CfGenC6Sxacih1sn9aDeYSR9lbRmzijeZ2mMDBMF2gKIHJquHNTsOLTvduRU5baaSVfwdg/WJIOpLLXPDnLdfvar31yi26r65wdN85Z8otv5MwRdJS8wV45FaBd2sLHSLlp4s42angSDvRrDgC+2xDT5xKv9vg3IhrYAHdsr0FLlwIDAQAB"
  }
  ```

* **MINING COIN**
  * `POST` `/blockchain/wallet/mine/{{amount}}`
    * **URL Params**:
      * `amount`: A number corresponding to the number of coins you want to credit on the wallet
    * **Headers**:
      * `private-key`: Private Key from the wallet you want to credit the amount mined
  * **Description**: This endpoint returns a transaction with the amount mined, sender and receiver public keys (in PEM String format). The amount informed will be the time elapsed to mine (in milliseconds). 
  * **Curl Example**:
  ```bash
  curl --location --request POST 'http://localhost:9093/blockchain/wallet/mine/1000' --header 'private-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4zA8UtIl9mePVbzw2A/8NVfJtTRZQuK5dcUqYOkZndjZebgRAd5lB9mMrM99yQcTwF7oTxWc9a8K74KN0KCaGO49nPuO+d2TZ9JjHoWHeasB8qhEVQXBQaFxP9YDZ9qmEZM6VFzfWoELjgEvPE5HH8MtcEKHZWTbsKumSEv/oJ8Z6cLpLFpyKHWyf1oN5hJH2VtGbOKN5naYwMEwXaAogcmq4c1Ow4tO925FTltppJV/B2D9Ykg6kstc8Oct1+9qvfXKLbqvrnB03zlnyi2/kzBF0lLzBXjkVoF3awsdIuWnizjZqeBIO9GsOAL7bENPnEq/2+DciGtgAd2yvQUuXAgMBAAECggEAAznHzKnNxwOsiLlcbP16LwYRHzozbqZjd4YlwvPpP0d0k1eOOxNt5QfKERkETxdYBOLe0zIao/MDJde56JXrJgXLjUKvYFaegcK46f8p7U8bkdaQVzH8QO7ZauzWlwhBFRmrlLyZn0OY8JFQc/mpZRJb0mI98JVJo0X5pRXR3rzPaTEwaypW/2nnhVoUavSKHjuXn7LXZAklIdqrHVsLEqcmAY3XCi7Sxk1dj50CKKyWTBEy7VIAil2eTXSQhpKzypvq4CN8RW8UFxWmvwiu25Qb6HiytuN2MiB+HdZFfg30FomGnksHDL9PRlKa0uZZlHwMvN4Wi/jOH7u/YonEaQKBgQDZ0kZQrN1wfSTV0b3MYTrfaISg5c9JlPQScWZIMDbruP+2yOLZmG3ci+IBnR/PoUK7kHUdX1xDqHSXdFFQ1rgkI/EAXq/QELQYttWway9eyHMohNIQPYoJB8vQVc82rxFzr5/izZHeRrQ5eYc1cRuyYFK9E7L9BdWyJm6m4FwxDwKBgQDZL/kCN20hIPZmt5iEN88ztYlRFgEpMf0QIG0MBMQuxFOh4ciDXxcuZ+F1fOuEruDvIYv33OlomqaU+l1/Ufe6d9mu90r72a+DZ4MgZEn1et94ZFCjpPlPh0okffr53Q/OO8VkLwx/h5R3SykK9W8/2zjGu6cpjIkV1Lc/omks+QKBgDguzIQykktZfA7KsrHswkqtosf9fqH0EaBQL5mfFgtnBE4ZMqn9bxjb28eex32+kn/emtagqiOpZ6lJD1BSkdyAiMEXqzF3nXxibGu8udMw0MM6vxQBS4FYyXFLMM9iCJZWTl8ptt4mToYkmoNmUUaJIc4zLo+k2aHB1/ijFiRxAoGBAJliM+3Izx1ydhIc322hLIhMUOofLDq3vw1AmUK/qv2MUbQmDMIayfhQDP44duyBxQGdI4itT3o8niDgoF9muBnMwsO6ig5LspSO6iBaM5FWcbRIxFqODDSKS2ZyQmlsIdl4hPM39w+p5MRLJHVE2Ii0feh8ZpYFVltAmxopzTWhAoGBALlwONOKxm+jMNgxX32cC2Q3yGYf44fuzQQYSuzF/ROGse3K2bLV2T6+Y4lfKYY1BbkBtYB6kbayxWxbQpUk/9LojCS33D32/zO95G9MkAFVy59D+zo1jedqVM2Nlv8yGQk5s+02149I+TmJKQQX43f/btNR4Z9WsYh2yf4lQpea'
  ```
  * **Response Example**:
  ```json
  {
    "amount": 1000,
    "senderPublicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuMwPFLSJfZnj1W88NgP/DVXybU0WULiuXXFKmDpGZ3Y2Xm4EQHeZQfZjKzPfckHE8Be6E8VnPWvCu+CjdCgmhjuPZz7jvndk2fSYx6Fh3mrAfKoRFUFwUGhcT/WA2faphGTOlRc31qBC44BLzxORx/DLXBCh2Vk27CrpkhL/6CfGenC6Sxacih1sn9aDeYSR9lbRmzijeZ2mMDBMF2gKIHJquHNTsOLTvduRU5baaSVfwdg/WJIOpLLXPDnLdfvar31yi26r65wdN85Z8otv5MwRdJS8wV45FaBd2sLHSLlp4s42angSDvRrDgC+2xDT5xKv9vg3IhrYAHdsr0FLlwIDAQAB",
    "receiverPublicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuMwPFLSJfZnj1W88NgP/DVXybU0WULiuXXFKmDpGZ3Y2Xm4EQHeZQfZjKzPfckHE8Be6E8VnPWvCu+CjdCgmhjuPZz7jvndk2fSYx6Fh3mrAfKoRFUFwUGhcT/WA2faphGTOlRc31qBC44BLzxORx/DLXBCh2Vk27CrpkhL/6CfGenC6Sxacih1sn9aDeYSR9lbRmzijeZ2mMDBMF2gKIHJquHNTsOLTvduRU5baaSVfwdg/WJIOpLLXPDnLdfvar31yi26r65wdN85Z8otv5MwRdJS8wV45FaBd2sLHSLlp4s42angSDvRrDgC+2xDT5xKv9vg3IhrYAHdsr0FLlwIDAQAB"
  }
  ```

* **SENDING COIN**
  * `POST` `/blockchain/wallet/send/{{amount}}`
    * **URL Params**:
      * `amount`: A number corresponding to the number of coins you want to send
    * **Headers**:
      * `sender-private-key`: Private Key from the wallet you want to debit the amount
      * `recceiver-public-key`: Public Key from the wallet you want to credit the amount
  * **Description**: This endpoint returns a transaction with the amount sent, sender and receiver public keys (in PEM String format)
  * **Curl Example**:
  ```bash
  curl --location --request POST 'http://localhost:9093/blockchain/wallet/send/200' --header 'sender-private-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4zA8UtIl9mePVbzw2A/8NVfJtTRZQuK5dcUqYOkZndjZebgRAd5lB9mMrM99yQcTwF7oTxWc9a8K74KN0KCaGO49nPuO+d2TZ9JjHoWHeasB8qhEVQXBQaFxP9YDZ9qmEZM6VFzfWoELjgEvPE5HH8MtcEKHZWTbsKumSEv/oJ8Z6cLpLFpyKHWyf1oN5hJH2VtGbOKN5naYwMEwXaAogcmq4c1Ow4tO925FTltppJV/B2D9Ykg6kstc8Oct1+9qvfXKLbqvrnB03zlnyi2/kzBF0lLzBXjkVoF3awsdIuWnizjZqeBIO9GsOAL7bENPnEq/2+DciGtgAd2yvQUuXAgMBAAECggEAAznHzKnNxwOsiLlcbP16LwYRHzozbqZjd4YlwvPpP0d0k1eOOxNt5QfKERkETxdYBOLe0zIao/MDJde56JXrJgXLjUKvYFaegcK46f8p7U8bkdaQVzH8QO7ZauzWlwhBFRmrlLyZn0OY8JFQc/mpZRJb0mI98JVJo0X5pRXR3rzPaTEwaypW/2nnhVoUavSKHjuXn7LXZAklIdqrHVsLEqcmAY3XCi7Sxk1dj50CKKyWTBEy7VIAil2eTXSQhpKzypvq4CN8RW8UFxWmvwiu25Qb6HiytuN2MiB+HdZFfg30FomGnksHDL9PRlKa0uZZlHwMvN4Wi/jOH7u/YonEaQKBgQDZ0kZQrN1wfSTV0b3MYTrfaISg5c9JlPQScWZIMDbruP+2yOLZmG3ci+IBnR/PoUK7kHUdX1xDqHSXdFFQ1rgkI/EAXq/QELQYttWway9eyHMohNIQPYoJB8vQVc82rxFzr5/izZHeRrQ5eYc1cRuyYFK9E7L9BdWyJm6m4FwxDwKBgQDZL/kCN20hIPZmt5iEN88ztYlRFgEpMf0QIG0MBMQuxFOh4ciDXxcuZ+F1fOuEruDvIYv33OlomqaU+l1/Ufe6d9mu90r72a+DZ4MgZEn1et94ZFCjpPlPh0okffr53Q/OO8VkLwx/h5R3SykK9W8/2zjGu6cpjIkV1Lc/omks+QKBgDguzIQykktZfA7KsrHswkqtosf9fqH0EaBQL5mfFgtnBE4ZMqn9bxjb28eex32+kn/emtagqiOpZ6lJD1BSkdyAiMEXqzF3nXxibGu8udMw0MM6vxQBS4FYyXFLMM9iCJZWTl8ptt4mToYkmoNmUUaJIc4zLo+k2aHB1/ijFiRxAoGBAJliM+3Izx1ydhIc322hLIhMUOofLDq3vw1AmUK/qv2MUbQmDMIayfhQDP44duyBxQGdI4itT3o8niDgoF9muBnMwsO6ig5LspSO6iBaM5FWcbRIxFqODDSKS2ZyQmlsIdl4hPM39w+p5MRLJHVE2Ii0feh8ZpYFVltAmxopzTWhAoGBALlwONOKxm+jMNgxX32cC2Q3yGYf44fuzQQYSuzF/ROGse3K2bLV2T6+Y4lfKYY1BbkBtYB6kbayxWxbQpUk/9LojCS33D32/zO95G9MkAFVy59D+zo1jedqVM2Nlv8yGQk5s+02149I+TmJKQQX43f/btNR4Z9WsYh2yf4lQpea' --header 'receiver-public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjRCr8Ub5rNGq6gw5gxvwP3Yw8kdxmhFgRrfMBkArwsvmwsavZw0wxWwy9Z+Kh6s/osPj62KqpyJYzdVfkoEPSj/DxMGNilhYym+3uRGCYWuwyK6fb/mYDPipALs+KeCIKq218+RmWfWa7V6/CnJIsJToEp1itHQp7/nGQiiYN1SG/gWjf6JgjrQjq3urkGo2Q7YbIs1vMQo5o32Q7pBnb3dq7h+aA4IvMXqvUxfTvHQ15mlE60pdUULcka3NnrYCx4599d+darixIurYyZ5/hrPL9Se5eFBO1PTlcAv80xwP/DpzFQkFWDoA1uuLjZZBMkNoE48lBSlD+0J1ZI+3pQIDAQAB'
  ```
  * **Response Example**:
  ```json
  {
    "amount": 200,
    "senderPublicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuMwPFLSJfZnj1W88NgP/DVXybU0WULiuXXFKmDpGZ3Y2Xm4EQHeZQfZjKzPfckHE8Be6E8VnPWvCu+CjdCgmhjuPZz7jvndk2fSYx6Fh3mrAfKoRFUFwUGhcT/WA2faphGTOlRc31qBC44BLzxORx/DLXBCh2Vk27CrpkhL/6CfGenC6Sxacih1sn9aDeYSR9lbRmzijeZ2mMDBMF2gKIHJquHNTsOLTvduRU5baaSVfwdg/WJIOpLLXPDnLdfvar31yi26r65wdN85Z8otv5MwRdJS8wV45FaBd2sLHSLlp4s42angSDvRrDgC+2xDT5xKv9vg3IhrYAHdsr0FLlwIDAQAB",
    "receiverPublicKey": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjRCr8Ub5rNGq6gw5gxvwP3Yw8kdxmhFgRrfMBkArwsvmwsavZw0wxWwy9Z+Kh6s/osPj62KqpyJYzdVfkoEPSj/DxMGNilhYym+3uRGCYWuwyK6fb/mYDPipALs+KeCIKq218+RmWfWa7V6/CnJIsJToEp1itHQp7/nGQiiYN1SG/gWjf6JgjrQjq3urkGo2Q7YbIs1vMQo5o32Q7pBnb3dq7h+aA4IvMXqvUxfTvHQ15mlE60pdUULcka3NnrYCx4599d+darixIurYyZ5/hrPL9Se5eFBO1PTlcAv80xwP/DpzFQkFWDoA1uuLjZZBMkNoE48lBSlD+0J1ZI+3pQIDAQAB"
  }
  ```


## Postman

To use this api you can install Postman and import the collection available on `postman` folder.

To import the collection on Postman:
1. Select the Menu `File > Import`
2. Click on `Upload Files` button (`File` tab)
3. Select `./postman/Blockchain.postman_collection.json` file to import.


## Disclaimer

This project was implemented as a personal blockchain study, doesn't relate with other existing blockchains, and should not be used as a final solution.

