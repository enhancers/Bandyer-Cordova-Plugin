//
// Copyright © 2019 Bandyer S.r.l. All rights reserved.
// See LICENSE.txt for licensing information
//

#import <OCHamcrest/OCHamcrest.h>

#import "BCPTestCase.h"
#import "BCPTestingMacros.h"
#import "BCPExceptionsMatcher.h"

#import "BCPUsersDetailsProvider.h"
#import "BCPUsersDetailsCache.h"

@interface BCPUsersDetailsProviderTest : BCPTestCase

@end

@implementation BCPUsersDetailsProviderTest
{
    BCPUsersDetailsCache *cache;
    BCPUsersDetailsProvider *sut;
    BDKUserInfoDisplayItem *item1;
    BDKUserInfoDisplayItem *item2;
}

- (void)setUp
{
    [super setUp];

    item1 = [[BDKUserInfoDisplayItem alloc] initWithAlias:@"alias1"];
    item2 = [[BDKUserInfoDisplayItem alloc] initWithAlias:@"alias2"];

    cache = [BCPUsersDetailsCache new];
    sut = [[BCPUsersDetailsProvider alloc] initWithCache:cache];
}

__SUPPRESS_WARNINGS_FOR_TEST_BEGIN

- (void)testThrowsInvalidArgumentExceptionWhenNilCacheIsProvidedInInitialization
{
    assertThat(^{[[BCPUsersDetailsProvider alloc] initWithCache:nil];}, throwsInvalidArgumentException());
}

- (void)testFetchesUsersDetailsFromCache
{
    [cache setItem:item1 forKey:item1.alias];
    [cache setItem:item2 forKey:item2.alias];

    XCTestExpectation *expc = [self expectationWithDescription:@"Callback invocation expectation"];

    [sut fetchUsers:@[item1.alias, item2.alias] completion:^(NSArray<BDKUserInfoDisplayItem *> *items) {
        [expc fulfill];

        assertThat(items, containsIn(@[item1, item2]));
    }];

    [self waitForExpectations:@[expc] timeout:0];
}

- (void)testCreatesItemWhenOneCouldNotBeFoundInTheCache
{
    [cache setItem:item1 forKey:item1.alias];

    XCTestExpectation *expc = [self expectationWithDescription:@"Callback invocation expectation"];

    [sut fetchUsers:@[item1.alias, @"foo.bar"] completion:^(NSArray<BDKUserInfoDisplayItem *> *items) {
        [expc fulfill];

        assertThat(items, hasCountOf(2));

        BDKUserInfoDisplayItem *missingItem = [[BDKUserInfoDisplayItem alloc] initWithAlias:@"foo.bar"];
        assertThat(items, containsIn(@[item1, missingItem]));
    }];

    [self waitForExpectations:@[expc] timeout:0];
}

- (void)testCopyReturnsACopy
{
    BCPUsersDetailsProvider *copy = [sut copy];

    assertThat(copy, notNilValue());
    assertThat(copy, isNot(sameInstance(sut)));
}

__SUPPRESS_WARNINGS_FOR_TEST_END

@end
