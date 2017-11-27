package com.xutrade.service;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {
    @Test
    public void getPrefixedMessage() throws Exception {
        AuthenticationService service = new AuthenticationService();
        byte[] message = service.getPrefixedMessage("hello");
        byte[] hashedMessage = Hash.sha3(message);

        String expected = "0x50b2c43fd39106bafbba0da34fc430e1f91e3c96ea2acee2bc34119f92b37750";
        Assert.assertEquals(expected, Numeric.toHexString(hashedMessage));
    }

    @Test
    public void getSenderAddress() throws Exception {
        AuthenticationService service = new AuthenticationService();
        byte[] message = service.getPrefixedMessage("hello");

        byte[] r = Numeric.hexStringToByteArray("0x1845faa75f53acb0c3e7247dcf294ce045c139722418dc9638709b54bafffa09");
        byte[] s = Numeric.hexStringToByteArray("0x3591aeaaa195e7dc53f7e774c80e9a7f1371f0647a100d1c9e81db83d8ddd478");
        byte v = Numeric.hexStringToByteArray("0x1c")[0];

        String address = service.getSenderAddress(message, r, s, v);
        Assert.assertEquals("90f8bf6a479f320ead074411a4b0e7944ea8c9c1", address);
    }
}