package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.webapi.RootController
import org.junit.jupiter.api.Test

/**
 * @author tbrooks
 */
class RootControllerTest {

    @Test
    void testRootControllerEndpoints(){
        RootController rootController = new RootController()
        assert rootController.index().getBody().get()[0].contains("ping")
        assert rootController.index().getBody().get()[1].contains("help")
    }

    @Test
    void testRootControllerPing(){
        RootController rootController = new RootController()
        assert rootController.ping() == "pong"
    }
    @Test
    void testRootControllerHelp(){
        RootController rootController = new RootController()
        assert rootController.help()
    }
}
