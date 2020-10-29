package org.mcnative.licensing.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Base64;
import java.util.Enumeration;

public class LicenseUtil {

    /**
     * Returns a unique hardware id for this device.
     *
     * @return The id
     */
    public static String getDeviceId(){
        try{
            ByteBuf buffer = Unpooled.directBuffer();
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface networkInterface = interfaces.nextElement();
                if(networkInterface.getHardwareAddress() != null){
                    buffer.writeBytes(networkInterface.getHardwareAddress());
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    buffer.writeShort(interfaceAddress.getNetworkPrefixLength());
                    buffer.writeBytes(interfaceAddress.getAddress().getAddress());
                }
            }
            byte[] result = new byte[buffer.readableBytes()];
            buffer.readBytes(result);
            return Base64.getEncoder().encodeToString(result);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
