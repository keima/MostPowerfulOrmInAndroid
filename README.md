# Most Powerful ORM in Android

## Check Dependency is Outdated

1. `./gradlew dependencyUpdates`

## How to use

1. git clone https://github.com/keima/MostPowerfulOrmInAndroid.git
2. cd <PATH_TO_GIT>/MostPowerfulOrmInAndroid
3. Connect device or emulator
4. ./gradlew connectedAndroidTest<ProductionFlavor>Debug

## Available Production Flavors

- <u>appOrmlite</u>:connectedDebugAndroidTest
- <u>appActiveandroid</u>:connectedDebugAndroidTest
- <u>appGreendao</u>:connectedDebugAndroidTest
- <u>appSugarorm</u>:connectedDebugAndroidTest
- <u>appDbflow</u>:connectedDebugAndroidTest
- <u>appRealm</u>:connectedDebugAndroidTest
- <u>appCouchbase</u>:connectedDebugAndroidTest

# License

```
Copyright (C) 2014-2016 Kouta Imanaka. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
```

This project contains following libraries:

- Android Support Library
- ORMLite
- ActiveAndroid
- greenDAO
- SugarORM
- (comment-out: Ollie)
- DBFlow
- Realm
- couchbase-lite
