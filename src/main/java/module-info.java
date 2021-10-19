module omnirestcli {
    requires omnij.net.api;

    requires omnij.rest.client.mjdk;
    requires omnij.core;
    requires omnij.money;
    requires omnij.jsonrpc;
    requires bitcoinj.core;

    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    requires org.slf4j;

    exports foundation.omni.sample.maven;
}
