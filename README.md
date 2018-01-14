# Crypto Launchpad
An aggregation of various public APIs that contain data about cryptocurrency.

## Including the library in your project
Crypto Launchpad is still in development and does not have any published releases. For now, you can use
the library through [JitPack](https://www.jitpack.io).

Add JitPack in your root `build.gradle` at the end of repositories:

    allprojects {
    repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

Then add the project to your project's `build.gradle` dependency block.

    dependencies {
        implementation 'com.github.harenj:crypto-launchpad-android:mainline-SNAPSHOT'
    }
    
And that's it! This library uses [OkHttp](https://github.com/square/okhttp) and [Gson](https://github.com/google/gson)
under the hood. Those dependencies are included automatically.
    
## Usage

Currently there is one website supported, which is [CoinMarketCap](www.coinmarketcap.com).

To get the data, do the following:

    CoinMarketCapClient.getTicker(new ClientListener<List<Ticker>>() {
        @Override
        public void onSuccess(List<Ticker> data) {
            
        }

        @Override
        public void onError(Exception e) {

        }
    });

You can do whatever you'd like with the data in the `onSuccess()` callback from `ClientListener`.

## Future

More to come, stay tuned.
