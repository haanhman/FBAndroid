//
//  MainScene.m
//  PROJECTNAME
//
//  Created by Viktor on 10/10/13.
//  Copyright (c) 2013 Apportable. All rights reserved.
//

#import "MainScene.h"

#if ANDROID
#import "FacebookConnect.h"
#import <BridgeKit/AndroidActivity.h>
#endif

@implementation MainScene {
    #if ANDROID
    FacebookConnect *bridgeObject;
    #endif
}


-(void)onEnter {
    [super onEnter];
    #if ANDROID
    bridgeObject = [[FacebookConnect alloc] init];
    #endif
}

//ld1cC5/BVRekvCebpMZd7cM8rlU=
//T5FjoWRtFURA+6Wyux7zCtTNZok=

-(void)loginAction {
    NSLog(@"==> login action");
#if ANDROID
    AndroidActivity *activity     = [AndroidActivity currentActivity];
    [bridgeObject initFB:activity];
    [bridgeObject loginFB:activity];
#endif
}


-(void)loginStatus {
#if ANDROID
    NSLog(@"==> loginStatus: %d", bridgeObject.loginStatus);
#endif
    
}

//- (id)init
//{
//#if ANDROID
//    FacebookConnect *bridgeObject = [[FacebookConnect alloc] init];
//    AndroidActivity *activity     = [AndroidActivity currentActivity];
//    [bridgeObject initFB:activity];
//#endif
//    return self;
//}

@end
