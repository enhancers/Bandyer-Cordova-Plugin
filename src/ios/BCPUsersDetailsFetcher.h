//
// Copyright © 2019 Bandyer S.r.l. All rights reserved.
// See LICENSE.txt for licensing information
//

#import <Foundation/Foundation.h>
#import <BandyerSDK/BDKUserInfoFetcher.h>

@class BCPUsersDetailsCache;

NS_ASSUME_NONNULL_BEGIN

@interface BCPUsersDetailsFetcher : NSObject <BDKUserInfoFetcher>

- (instancetype)initWithCache:(BCPUsersDetailsCache *)cache;

- (instancetype)init NS_UNAVAILABLE;
+ (instancetype)new NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
