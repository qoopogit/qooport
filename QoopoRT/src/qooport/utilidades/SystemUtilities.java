/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

/**
 *
 * @author alberto
 */
public abstract class SystemUtilities {

    public static String getRamInfo() {
        long maxMG = Runtime.getRuntime().maxMemory();
        long freeMG = Runtime.getRuntime().freeMemory();
        long totalMG = Runtime.getRuntime().totalMemory();
        return new StringBuilder().append(Util.convertirBytes(totalMG - freeMG)).append("/").append(Util.convertirBytes(totalMG)).append(" [").append(Util.convertirBytes(maxMG)).append("]").toString();
    }

//    public static String getDefaultLookAndFeel() {
//        for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
//            if (lookAndFeelInfo.getName().equals("Nimbus")) {
//                return lookAndFeelInfo.getClassName();
//            }
//        }
//        return MetalLookAndFeel.class.getName();
//    }
}
