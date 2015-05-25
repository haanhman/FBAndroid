//
//  VideoPlayer.m
//  AndroidNative
//
//  Created by anhmantk on 12/4/14.
//  Copyright 2014 Apportable. All rights reserved.
//
#if ANDROID
#import "FacebookConnect.h"

@implementation FacebookConnect

+ (void)initializeJava
{
    [super initializeJava];
    [FacebookConnect registerInstanceMethod:@"initFB"
                                  selector:@selector(initFB:)
                                 arguments:@"android/app/Activity", nil];
    [FacebookConnect registerInstanceMethod:@"loginFB"
                                   selector:@selector(loginFB:)
                                  arguments:@"android/app/Activity", nil];
    [FacebookConnect registerInstanceMethod:@"getLoginStatus"
                               selector:@selector(loginStatus)
                            returnValue:[JavaClass intPrimitive]];
}

+ (NSString *)className
{
    return @"com.anhmantk.FacebookConnect";
}

@end
#endif