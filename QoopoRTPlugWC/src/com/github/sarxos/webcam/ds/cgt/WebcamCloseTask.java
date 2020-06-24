package com.github.sarxos.webcam.ds.cgt;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;

public class WebcamCloseTask extends WebcamTask {

    public WebcamCloseTask(WebcamDriver driver, WebcamDevice device) {
        super(driver, device);
    }

    public void close() throws InterruptedException {
        process();
    }

    @Override
    protected void handle() {
        WebcamDevice device = getDevice();
        if (!device.isOpen()) {
            return;
        }
        device.close();
    }
}
