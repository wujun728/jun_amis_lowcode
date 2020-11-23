package org.kiwipeach.demo.service;

import org.kiwipeach.demo.config.HelloProperties;

/**
 * 描述：
 *
 * @author Wujun
 * @since 2.0
 */

public class HelloService {

    private HelloProperties helloProperties;

    public String sayHello(String word) {
        return String.format("[%s]-%s-[%s]", helloProperties.getPrefix(), word, helloProperties.getSuffix());
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
