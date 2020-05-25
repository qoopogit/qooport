/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.tcp;

import java.io.IOException;

/**
 *
 * @author alberto
 */
public interface QServerSocket {

    public abstract QSocket accept() throws IOException;

}
