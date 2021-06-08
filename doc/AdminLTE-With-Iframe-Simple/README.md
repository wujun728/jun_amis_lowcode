# AdminLTE-With-Iframe-Simple

Project Url   
 [https://gitee.com/fallstar/AdminLTE-With-Iframe-Simple](https://gitee.com/fallstar/AdminLTE-With-Iframe-Simple)   

[https://github.com/FallStar0/AdminLTE-With-Iframe-Simple](https://github.com/FallStar0/AdminLTE-With-Iframe-Simple)

[Chinese Version](README.zh-cn.md)

For the user who don't want to use complex fontend project or lack of time or peaple.

## Instruction

The full version of the project comes from this project :

[https://gitee.com/weituotian/AdminLTE-With-Iframe](https://gitee.com/weituotian/AdminLTE-With-Iframe "AdminLTE-With-Iframe")

This project has too many dependences ,so I had to make it simple to use.

Only base framework from AdminLTE and tab manange with iframes.

Change skin function is still avalible.

## Features

+ Build the menus by passing an tree structure config model.
+ Menu kind support: virtual , external , internal ,etc
+ Multi tabs support

## Scense

The background project using ***ASP.NET*** or ***ASP.NET Core*** , import this sample assets,
pass the menu configs to the page and bind.

Then the framework is done.

The other functions can develop in seperate form.

The best partner of this project : vue.js , bootstrap , jQuery , Layer


## Shot
![](./asset/Shot1.jpg)

![](./asset/Shot2.jpg)

## Supplement

About how to open new tab in child pages :

```
//include the follow js in child page
// this js contains tab methods : add / close / refresh
// you can extend it by your self
tab.util.js

call method:
//Open an external link
Util.addTab('https://google.com','google','Google',true});
//Open a relative link
Util.addTab('/test/404.html','404','404 Not Found'});
//Close current page
Util.closeTab();
```