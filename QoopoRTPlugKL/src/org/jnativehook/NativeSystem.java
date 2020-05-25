package org.jnativehook;

public class NativeSystem {

    public static Family getFamily() {
        String str = System.getProperty("os.name");
        Family localFamily;
        if (str.equalsIgnoreCase("freebsd")) {
            localFamily = Family.FREEBSD;
        } else if (str.equalsIgnoreCase("openbsd")) {
            localFamily = Family.OPENBSD;
        } else if (str.equalsIgnoreCase("mac os x")) {
            localFamily = Family.OSX;
        } else if ((str.equalsIgnoreCase("solaris")) || (str.equalsIgnoreCase("sunos"))) {
            localFamily = Family.SOLARIS;
        } else if (str.equalsIgnoreCase("linux")) {
            localFamily = Family.LINUX;
        } else if (str.toLowerCase().startsWith("windows")) {
            localFamily = Family.WINDOWS;
        } else {
            localFamily = Family.UNSUPPORTED;
        }
        return localFamily;
    }

    public static Arch getArchitecture() {
        String str = System.getProperty("os.arch");
        Arch localArch;
        if (str.equalsIgnoreCase("alpha")) {
            localArch = Arch.ALPHA;
        } else if (str.toLowerCase().startsWith("arm")) {
            localArch = Arch.ARM;
        } else if (str.equalsIgnoreCase("ia64_32")) {
            localArch = Arch.IA64_32;
        } else if (str.equalsIgnoreCase("ia64")) {
            localArch = Arch.IA64;
        } else if (str.equalsIgnoreCase("mips")) {
            localArch = Arch.MIPS;
        } else if (str.equalsIgnoreCase("sparc")) {
            localArch = Arch.SPARC;
        } else if (str.equalsIgnoreCase("sparc64")) {
            localArch = Arch.SPARC64;
        } else if ((str.equalsIgnoreCase("ppc")) || (str.equalsIgnoreCase("powerpc"))) {
            localArch = Arch.PPC;
        } else if ((str.equalsIgnoreCase("ppc64")) || (str.equalsIgnoreCase("powerpc64"))) {
            localArch = Arch.PPC64;
        } else if ((str.equalsIgnoreCase("x86")) || (str.equalsIgnoreCase("i386")) || (str.equalsIgnoreCase("i486")) || (str.equalsIgnoreCase("i586")) || (str.equalsIgnoreCase("i686"))) {
            localArch = Arch.x86;
        } else if ((str.equalsIgnoreCase("x86_64")) || (str.equalsIgnoreCase("amd64")) || (str.equalsIgnoreCase("k8"))) {
            localArch = Arch.x86_64;
        } else {
            localArch = Arch.UNSUPPORTED;
        }
        return localArch;
    }

    public static enum Arch {

        ALPHA, ARM, IA64_32, IA64, MIPS, SPARC, SPARC64, PPC, PPC64, x86, x86_64, UNSUPPORTED;
    }

    public static enum Family {

        FREEBSD, OPENBSD, OSX, SOLARIS, LINUX, WINDOWS, UNSUPPORTED;
    }
}
