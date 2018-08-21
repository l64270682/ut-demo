package com.xhtec.utdemo;

import com.xhtec.utdemo.interfaces.http.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author hhy@100fen.cn
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShelvesControllerTests.class,
        ShoppingCartControllerTests.class,
        OrderControllerTests.class,
        PayControllerTests.class
})
public class InterfacesTestSuite {

}
