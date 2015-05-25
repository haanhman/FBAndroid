#if ANDROID
#import <Foundation/Foundation.h>
#import <BridgeKit/JavaObject.h>
#import <BridgeKit/AndroidActivity.h>
#import "cocos2d.h"

@interface FacebookConnect : JavaObject
@property (nonatomic, assign) int loginStatus;
- (void)initFB:(AndroidActivity*)activity;
- (void)loginFB:(AndroidActivity*)activity;
@end
#endif