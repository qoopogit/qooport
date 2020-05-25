package com.maxmind.geoip;

public class timeZone {

    public static String timeZoneByCountryAndRegion(String country, String region) {
        /*    5 */ String timezone = null;
        /*    6 */ if (country == null) {
            /*    7 */ return null;
        }
        /*    9 */ if (region == null) {
            /*   10 */ region = "";
        }
        /*   12 */ if (country.equals("AD") == true) /*   13 */ {
            timezone = "Europe/Andorra";
        } /*   14 */ else if (country.equals("AE") == true) /*   15 */ {
            timezone = "Asia/Dubai";
        } /*   16 */ else if (country.equals("AF") == true) /*   17 */ {
            timezone = "Asia/Kabul";
        } /*   18 */ else if (country.equals("AG") == true) /*   19 */ {
            timezone = "America/Antigua";
        } /*   20 */ else if (country.equals("AI") == true) /*   21 */ {
            timezone = "America/Anguilla";
        } /*   22 */ else if (country.equals("AL") == true) /*   23 */ {
            timezone = "Europe/Tirane";
        } /*   24 */ else if (country.equals("AM") == true) /*   25 */ {
            timezone = "Asia/Yerevan";
        } /*   26 */ else if (country.equals("AN") == true) /*   27 */ {
            timezone = "America/Curacao";
        } /*   28 */ else if (country.equals("AO") == true) /*   29 */ {
            timezone = "Africa/Luanda";
        } /*   30 */ else if (country.equals("AQ") == true) /*   31 */ {
            timezone = "Antarctica/South_Pole";
        } /*   32 */ else if (country.equals("AR") == true) {
            /*   33 */ if (region.equals("01") == true) /*   34 */ {
                timezone = "America/Argentina/Buenos_Aires";
            } /*   35 */ else if (region.equals("02") == true) /*   36 */ {
                timezone = "America/Argentina/Catamarca";
            } /*   37 */ else if (region.equals("03") == true) /*   38 */ {
                timezone = "America/Argentina/Tucuman";
            } /*   39 */ else if (region.equals("04") == true) /*   40 */ {
                timezone = "America/Argentina/Rio_Gallegos";
            } /*   41 */ else if (region.equals("05") == true) /*   42 */ {
                timezone = "America/Argentina/Cordoba";
            } /*   43 */ else if (region.equals("06") == true) /*   44 */ {
                timezone = "America/Argentina/Buenos_Aires";
            } /*   45 */ else if (region.equals("07") == true) /*   46 */ {
                timezone = "America/Argentina/Buenos_Aires";
            } /*   47 */ else if (region.equals("08") == true) /*   48 */ {
                timezone = "America/Argentina/Buenos_Aires";
            } /*   49 */ else if (region.equals("09") == true) /*   50 */ {
                timezone = "America/Argentina/Tucuman";
            } /*   51 */ else if (region.equals("10") == true) /*   52 */ {
                timezone = "America/Argentina/Jujuy";
            } /*   53 */ else if (region.equals("11") == true) /*   54 */ {
                timezone = "America/Argentina/San_Luis";
            } /*   55 */ else if (region.equals("12") == true) /*   56 */ {
                timezone = "America/Argentina/La_Rioja";
            } /*   57 */ else if (region.equals("13") == true) /*   58 */ {
                timezone = "America/Argentina/Mendoza";
            } /*   59 */ else if (region.equals("14") == true) /*   60 */ {
                timezone = "America/Argentina/Buenos_Aires";
            } /*   61 */ else if (region.equals("15") == true) /*   62 */ {
                timezone = "America/Argentina/Mendoza";
            } /*   63 */ else if (region.equals("16") == true) /*   64 */ {
                timezone = "America/Argentina/San_Luis";
            } /*   65 */ else if (region.equals("17") == true) /*   66 */ {
                timezone = "America/Argentina/Salta";
            } /*   67 */ else if (region.equals("18") == true) /*   68 */ {
                timezone = "America/Argentina/San_Juan";
            } /*   69 */ else if (region.equals("19") == true) /*   70 */ {
                timezone = "America/Argentina/San_Luis";
            } /*   71 */ else if (region.equals("20") == true) /*   72 */ {
                timezone = "America/Argentina/Rio_Gallegos";
            } /*   73 */ else if (region.equals("21") == true) /*   74 */ {
                timezone = "America/Argentina/Cordoba";
            } /*   75 */ else if (region.equals("22") == true) /*   76 */ {
                timezone = "America/Argentina/Catamarca";
            } /*   77 */ else if (region.equals("23") == true) /*   78 */ {
                timezone = "America/Argentina/Ushuaia";
            } /*   79 */ else if (region.equals("24") == true) /*   80 */ {
                timezone = "America/Argentina/Tucuman";
            }
        } /*   82 */ else if (country.equals("AS") == true) /*   83 */ {
            timezone = "Pacific/Pago_Pago";
        } /*   84 */ else if (country.equals("AT") == true) /*   85 */ {
            timezone = "Europe/Vienna";
        } /*   86 */ else if (country.equals("AU") == true) {
            /*   87 */ if (region.equals("01") == true) /*   88 */ {
                timezone = "Australia/Sydney";
            } /*   89 */ else if (region.equals("02") == true) /*   90 */ {
                timezone = "Australia/Sydney";
            } /*   91 */ else if (region.equals("03") == true) /*   92 */ {
                timezone = "Australia/Darwin";
            } /*   93 */ else if (region.equals("04") == true) /*   94 */ {
                timezone = "Australia/Brisbane";
            } /*   95 */ else if (region.equals("05") == true) /*   96 */ {
                timezone = "Australia/Adelaide";
            } /*   97 */ else if (region.equals("06") == true) /*   98 */ {
                timezone = "Australia/Hobart";
            } /*   99 */ else if (region.equals("07") == true) /*  100 */ {
                timezone = "Australia/Melbourne";
            } /*  101 */ else if (region.equals("08") == true) /*  102 */ {
                timezone = "Australia/Perth";
            }
        } /*  104 */ else if (country.equals("AW") == true) /*  105 */ {
            timezone = "America/Aruba";
        } /*  106 */ else if (country.equals("AX") == true) /*  107 */ {
            timezone = "Europe/Mariehamn";
        } /*  108 */ else if (country.equals("AZ") == true) /*  109 */ {
            timezone = "Asia/Baku";
        } /*  110 */ else if (country.equals("BA") == true) /*  111 */ {
            timezone = "Europe/Sarajevo";
        } /*  112 */ else if (country.equals("BB") == true) /*  113 */ {
            timezone = "America/Barbados";
        } /*  114 */ else if (country.equals("BD") == true) /*  115 */ {
            timezone = "Asia/Dhaka";
        } /*  116 */ else if (country.equals("BE") == true) /*  117 */ {
            timezone = "Europe/Brussels";
        } /*  118 */ else if (country.equals("BF") == true) /*  119 */ {
            timezone = "Africa/Ouagadougou";
        } /*  120 */ else if (country.equals("BG") == true) /*  121 */ {
            timezone = "Europe/Sofia";
        } /*  122 */ else if (country.equals("BH") == true) /*  123 */ {
            timezone = "Asia/Bahrain";
        } /*  124 */ else if (country.equals("BI") == true) /*  125 */ {
            timezone = "Africa/Bujumbura";
        } /*  126 */ else if (country.equals("BJ") == true) /*  127 */ {
            timezone = "Africa/Porto-Novo";
        } /*  128 */ else if (country.equals("BM") == true) /*  129 */ {
            timezone = "Atlantic/Bermuda";
        } /*  130 */ else if (country.equals("BN") == true) /*  131 */ {
            timezone = "Asia/Brunei";
        } /*  132 */ else if (country.equals("BO") == true) /*  133 */ {
            timezone = "America/La_Paz";
        } /*  134 */ else if (country.equals("BR") == true) {
            /*  135 */ if (region.equals("01") == true) /*  136 */ {
                timezone = "America/Rio_Branco";
            } /*  137 */ else if (region.equals("02") == true) /*  138 */ {
                timezone = "America/Maceio";
            } /*  139 */ else if (region.equals("03") == true) /*  140 */ {
                timezone = "America/Belem";
            } /*  141 */ else if (region.equals("04") == true) /*  142 */ {
                timezone = "America/Manaus";
            } /*  143 */ else if (region.equals("05") == true) /*  144 */ {
                timezone = "America/Bahia";
            } /*  145 */ else if (region.equals("06") == true) /*  146 */ {
                timezone = "America/Fortaleza";
            } /*  147 */ else if (region.equals("07") == true) /*  148 */ {
                timezone = "America/Cuiaba";
            } /*  149 */ else if (region.equals("08") == true) /*  150 */ {
                timezone = "America/Sao_Paulo";
            } /*  151 */ else if (region.equals("11") == true) /*  152 */ {
                timezone = "America/Campo_Grande";
            } /*  153 */ else if (region.equals("13") == true) /*  154 */ {
                timezone = "America/Araguaina";
            } /*  155 */ else if (region.equals("14") == true) /*  156 */ {
                timezone = "America/Cuiaba";
            } /*  157 */ else if (region.equals("15") == true) /*  158 */ {
                timezone = "America/Sao_Paulo";
            } /*  159 */ else if (region.equals("16") == true) /*  160 */ {
                timezone = "America/Belem";
            } /*  161 */ else if (region.equals("17") == true) /*  162 */ {
                timezone = "America/Recife";
            } /*  163 */ else if (region.equals("18") == true) /*  164 */ {
                timezone = "America/Campo_Grande";
            } /*  165 */ else if (region.equals("20") == true) /*  166 */ {
                timezone = "America/Fortaleza";
            } /*  167 */ else if (region.equals("21") == true) /*  168 */ {
                timezone = "America/Sao_Paulo";
            } /*  169 */ else if (region.equals("22") == true) /*  170 */ {
                timezone = "America/Recife";
            } /*  171 */ else if (region.equals("23") == true) /*  172 */ {
                timezone = "America/Sao_Paulo";
            } /*  173 */ else if (region.equals("24") == true) /*  174 */ {
                timezone = "America/Porto_Velho";
            } /*  175 */ else if (region.equals("25") == true) /*  176 */ {
                timezone = "America/Boa_Vista";
            } /*  177 */ else if (region.equals("26") == true) /*  178 */ {
                timezone = "America/Sao_Paulo";
            } /*  179 */ else if (region.equals("27") == true) /*  180 */ {
                timezone = "America/Sao_Paulo";
            } /*  181 */ else if (region.equals("28") == true) /*  182 */ {
                timezone = "America/Maceio";
            } /*  183 */ else if (region.equals("29") == true) /*  184 */ {
                timezone = "America/Campo_Grande";
            } /*  185 */ else if (region.equals("30") == true) /*  186 */ {
                timezone = "America/Recife";
            } /*  187 */ else if (region.equals("31") == true) /*  188 */ {
                timezone = "America/Araguaina";
            }
        } /*  190 */ else if (country.equals("BS") == true) /*  191 */ {
            timezone = "America/Nassau";
        } /*  192 */ else if (country.equals("BT") == true) /*  193 */ {
            timezone = "Asia/Thimphu";
        } /*  194 */ else if (country.equals("BV") == true) /*  195 */ {
            timezone = "Antarctica/Syowa";
        } /*  196 */ else if (country.equals("BW") == true) /*  197 */ {
            timezone = "Africa/Gaborone";
        } /*  198 */ else if (country.equals("BY") == true) /*  199 */ {
            timezone = "Europe/Minsk";
        } /*  200 */ else if (country.equals("BZ") == true) /*  201 */ {
            timezone = "America/Belize";
        } /*  202 */ else if (country.equals("CA") == true) {
            /*  203 */ if (region.equals("AB") == true) /*  204 */ {
                timezone = "America/Edmonton";
            } /*  205 */ else if (region.equals("BC") == true) /*  206 */ {
                timezone = "America/Vancouver";
            } /*  207 */ else if (region.equals("MB") == true) /*  208 */ {
                timezone = "America/Winnipeg";
            } /*  209 */ else if (region.equals("NB") == true) /*  210 */ {
                timezone = "America/Halifax";
            } /*  211 */ else if (region.equals("NL") == true) /*  212 */ {
                timezone = "America/St_Johns";
            } /*  213 */ else if (region.equals("NS") == true) /*  214 */ {
                timezone = "America/Halifax";
            } /*  215 */ else if (region.equals("NT") == true) /*  216 */ {
                timezone = "America/Yellowknife";
            } /*  217 */ else if (region.equals("NU") == true) /*  218 */ {
                timezone = "America/Rankin_Inlet";
            } /*  219 */ else if (region.equals("ON") == true) /*  220 */ {
                timezone = "America/Rainy_River";
            } /*  221 */ else if (region.equals("PE") == true) /*  222 */ {
                timezone = "America/Halifax";
            } /*  223 */ else if (region.equals("QC") == true) /*  224 */ {
                timezone = "America/Montreal";
            } /*  225 */ else if (region.equals("SK") == true) /*  226 */ {
                timezone = "America/Regina";
            } /*  227 */ else if (region.equals("YT") == true) /*  228 */ {
                timezone = "America/Whitehorse";
            }
        } /*  230 */ else if (country.equals("CC") == true) /*  231 */ {
            timezone = "Indian/Cocos";
        } /*  232 */ else if (country.equals("CD") == true) {
            /*  233 */ if (region.equals("01") == true) /*  234 */ {
                timezone = "Africa/Kinshasa";
            } /*  235 */ else if (region.equals("02") == true) /*  236 */ {
                timezone = "Africa/Kinshasa";
            } /*  237 */ else if (region.equals("03") == true) /*  238 */ {
                timezone = "Africa/Kinshasa";
            } /*  239 */ else if (region.equals("04") == true) /*  240 */ {
                timezone = "Africa/Lubumbashi";
            } /*  241 */ else if (region.equals("05") == true) /*  242 */ {
                timezone = "Africa/Lubumbashi";
            } /*  243 */ else if (region.equals("06") == true) /*  244 */ {
                timezone = "Africa/Kinshasa";
            } /*  245 */ else if (region.equals("07") == true) /*  246 */ {
                timezone = "Africa/Lubumbashi";
            } /*  247 */ else if (region.equals("08") == true) /*  248 */ {
                timezone = "Africa/Kinshasa";
            } /*  249 */ else if (region.equals("09") == true) /*  250 */ {
                timezone = "Africa/Lubumbashi";
            } /*  251 */ else if (region.equals("10") == true) /*  252 */ {
                timezone = "Africa/Lubumbashi";
            } /*  253 */ else if (region.equals("11") == true) /*  254 */ {
                timezone = "Africa/Lubumbashi";
            } /*  255 */ else if (region.equals("12") == true) /*  256 */ {
                timezone = "Africa/Lubumbashi";
            }
        } /*  258 */ else if (country.equals("CF") == true) /*  259 */ {
            timezone = "Africa/Bangui";
        } /*  260 */ else if (country.equals("CG") == true) /*  261 */ {
            timezone = "Africa/Brazzaville";
        } /*  262 */ else if (country.equals("CH") == true) /*  263 */ {
            timezone = "Europe/Zurich";
        } /*  264 */ else if (country.equals("CI") == true) /*  265 */ {
            timezone = "Africa/Abidjan";
        } /*  266 */ else if (country.equals("CK") == true) /*  267 */ {
            timezone = "Pacific/Rarotonga";
        } /*  268 */ else if (country.equals("CL") == true) /*  269 */ {
            timezone = "America/Santiago";
        } /*  270 */ else if (country.equals("CM") == true) /*  271 */ {
            timezone = "Africa/Douala";
        } /*  272 */ else if (country.equals("CN") == true) {
            /*  273 */ if (region.equals("01") == true) /*  274 */ {
                timezone = "Asia/Shanghai";
            } /*  275 */ else if (region.equals("02") == true) /*  276 */ {
                timezone = "Asia/Shanghai";
            } /*  277 */ else if (region.equals("03") == true) /*  278 */ {
                timezone = "Asia/Shanghai";
            } /*  279 */ else if (region.equals("04") == true) /*  280 */ {
                timezone = "Asia/Shanghai";
            } /*  281 */ else if (region.equals("05") == true) /*  282 */ {
                timezone = "Asia/Harbin";
            } /*  283 */ else if (region.equals("06") == true) /*  284 */ {
                timezone = "Asia/Chongqing";
            } /*  285 */ else if (region.equals("07") == true) /*  286 */ {
                timezone = "Asia/Shanghai";
            } /*  287 */ else if (region.equals("08") == true) /*  288 */ {
                timezone = "Asia/Harbin";
            } /*  289 */ else if (region.equals("09") == true) /*  290 */ {
                timezone = "Asia/Shanghai";
            } /*  291 */ else if (region.equals("10") == true) /*  292 */ {
                timezone = "Asia/Shanghai";
            } /*  293 */ else if (region.equals("11") == true) /*  294 */ {
                timezone = "Asia/Chongqing";
            } /*  295 */ else if (region.equals("12") == true) /*  296 */ {
                timezone = "Asia/Chongqing";
            } /*  297 */ else if (region.equals("13") == true) /*  298 */ {
                timezone = "Asia/Urumqi";
            } /*  299 */ else if (region.equals("14") == true) /*  300 */ {
                timezone = "Asia/Chongqing";
            } /*  301 */ else if (region.equals("15") == true) /*  302 */ {
                timezone = "Asia/Chongqing";
            } /*  303 */ else if (region.equals("16") == true) /*  304 */ {
                timezone = "Asia/Chongqing";
            } /*  305 */ else if (region.equals("18") == true) /*  306 */ {
                timezone = "Asia/Chongqing";
            } /*  307 */ else if (region.equals("19") == true) /*  308 */ {
                timezone = "Asia/Harbin";
            } /*  309 */ else if (region.equals("20") == true) /*  310 */ {
                timezone = "Asia/Harbin";
            } /*  311 */ else if (region.equals("21") == true) /*  312 */ {
                timezone = "Asia/Chongqing";
            } /*  313 */ else if (region.equals("22") == true) /*  314 */ {
                timezone = "Asia/Harbin";
            } /*  315 */ else if (region.equals("23") == true) /*  316 */ {
                timezone = "Asia/Shanghai";
            } /*  317 */ else if (region.equals("24") == true) /*  318 */ {
                timezone = "Asia/Chongqing";
            } /*  319 */ else if (region.equals("25") == true) /*  320 */ {
                timezone = "Asia/Shanghai";
            } /*  321 */ else if (region.equals("26") == true) /*  322 */ {
                timezone = "Asia/Chongqing";
            } /*  323 */ else if (region.equals("28") == true) /*  324 */ {
                timezone = "Asia/Shanghai";
            } /*  325 */ else if (region.equals("29") == true) /*  326 */ {
                timezone = "Asia/Chongqing";
            } /*  327 */ else if (region.equals("30") == true) /*  328 */ {
                timezone = "Asia/Chongqing";
            } /*  329 */ else if (region.equals("31") == true) /*  330 */ {
                timezone = "Asia/Chongqing";
            } /*  331 */ else if (region.equals("32") == true) /*  332 */ {
                timezone = "Asia/Chongqing";
            } /*  333 */ else if (region.equals("33") == true) /*  334 */ {
                timezone = "Asia/Chongqing";
            }
        } /*  336 */ else if (country.equals("CO") == true) /*  337 */ {
            timezone = "America/Bogota";
        } /*  338 */ else if (country.equals("CR") == true) /*  339 */ {
            timezone = "America/Costa_Rica";
        } /*  340 */ else if (country.equals("CU") == true) /*  341 */ {
            timezone = "America/Havana";
        } /*  342 */ else if (country.equals("CV") == true) /*  343 */ {
            timezone = "Atlantic/Cape_Verde";
        } /*  344 */ else if (country.equals("CX") == true) /*  345 */ {
            timezone = "Indian/Christmas";
        } /*  346 */ else if (country.equals("CY") == true) /*  347 */ {
            timezone = "Asia/Nicosia";
        } /*  348 */ else if (country.equals("CZ") == true) /*  349 */ {
            timezone = "Europe/Prague";
        } /*  350 */ else if (country.equals("DE") == true) /*  351 */ {
            timezone = "Europe/Berlin";
        } /*  352 */ else if (country.equals("DJ") == true) /*  353 */ {
            timezone = "Africa/Djibouti";
        } /*  354 */ else if (country.equals("DK") == true) /*  355 */ {
            timezone = "Europe/Copenhagen";
        } /*  356 */ else if (country.equals("DM") == true) /*  357 */ {
            timezone = "America/Dominica";
        } /*  358 */ else if (country.equals("DO") == true) /*  359 */ {
            timezone = "America/Santo_Domingo";
        } /*  360 */ else if (country.equals("DZ") == true) /*  361 */ {
            timezone = "Africa/Algiers";
        } /*  362 */ else if (country.equals("EC") == true) {
            /*  363 */ if (region.equals("01") == true) /*  364 */ {
                timezone = "Pacific/Galapagos";
            } /*  365 */ else if (region.equals("02") == true) /*  366 */ {
                timezone = "America/Guayaquil";
            } /*  367 */ else if (region.equals("03") == true) /*  368 */ {
                timezone = "America/Guayaquil";
            } /*  369 */ else if (region.equals("04") == true) /*  370 */ {
                timezone = "America/Guayaquil";
            } /*  371 */ else if (region.equals("05") == true) /*  372 */ {
                timezone = "America/Guayaquil";
            } /*  373 */ else if (region.equals("06") == true) /*  374 */ {
                timezone = "America/Guayaquil";
            } /*  375 */ else if (region.equals("07") == true) /*  376 */ {
                timezone = "America/Guayaquil";
            } /*  377 */ else if (region.equals("08") == true) /*  378 */ {
                timezone = "America/Guayaquil";
            } /*  379 */ else if (region.equals("09") == true) /*  380 */ {
                timezone = "America/Guayaquil";
            } /*  381 */ else if (region.equals("10") == true) /*  382 */ {
                timezone = "America/Guayaquil";
            } /*  383 */ else if (region.equals("11") == true) /*  384 */ {
                timezone = "America/Guayaquil";
            } /*  385 */ else if (region.equals("12") == true) /*  386 */ {
                timezone = "America/Guayaquil";
            } /*  387 */ else if (region.equals("13") == true) /*  388 */ {
                timezone = "America/Guayaquil";
            } /*  389 */ else if (region.equals("14") == true) /*  390 */ {
                timezone = "America/Guayaquil";
            } /*  391 */ else if (region.equals("15") == true) /*  392 */ {
                timezone = "America/Guayaquil";
            } /*  393 */ else if (region.equals("17") == true) /*  394 */ {
                timezone = "America/Guayaquil";
            } /*  395 */ else if (region.equals("18") == true) /*  396 */ {
                timezone = "America/Guayaquil";
            } /*  397 */ else if (region.equals("19") == true) /*  398 */ {
                timezone = "America/Guayaquil";
            } /*  399 */ else if (region.equals("22") == true) /*  400 */ {
                timezone = "America/Guayaquil";
            } /*  401 */ else if (region.equals("24") == true) /*  402 */ {
                timezone = "America/Guayaquil";
            }
        } /*  404 */ else if (country.equals("EE") == true) /*  405 */ {
            timezone = "Europe/Tallinn";
        } /*  406 */ else if (country.equals("EG") == true) /*  407 */ {
            timezone = "Africa/Cairo";
        } /*  408 */ else if (country.equals("EH") == true) /*  409 */ {
            timezone = "Africa/El_Aaiun";
        } /*  410 */ else if (country.equals("ER") == true) /*  411 */ {
            timezone = "Africa/Asmara";
        } /*  412 */ else if (country.equals("ES") == true) {
            /*  413 */ if (region.equals("07") == true) /*  414 */ {
                timezone = "Europe/Madrid";
            } /*  415 */ else if (region.equals("27") == true) /*  416 */ {
                timezone = "Europe/Madrid";
            } /*  417 */ else if (region.equals("29") == true) /*  418 */ {
                timezone = "Europe/Madrid";
            } /*  419 */ else if (region.equals("31") == true) /*  420 */ {
                timezone = "Europe/Madrid";
            } /*  421 */ else if (region.equals("32") == true) /*  422 */ {
                timezone = "Europe/Madrid";
            } /*  423 */ else if (region.equals("34") == true) /*  424 */ {
                timezone = "Europe/Madrid";
            } /*  425 */ else if (region.equals("39") == true) /*  426 */ {
                timezone = "Europe/Madrid";
            } /*  427 */ else if (region.equals("51") == true) /*  428 */ {
                timezone = "Africa/Ceuta";
            } /*  429 */ else if (region.equals("52") == true) /*  430 */ {
                timezone = "Europe/Madrid";
            } /*  431 */ else if (region.equals("53") == true) /*  432 */ {
                timezone = "Atlantic/Canary";
            } /*  433 */ else if (region.equals("54") == true) /*  434 */ {
                timezone = "Europe/Madrid";
            } /*  435 */ else if (region.equals("55") == true) /*  436 */ {
                timezone = "Europe/Madrid";
            } /*  437 */ else if (region.equals("56") == true) /*  438 */ {
                timezone = "Europe/Madrid";
            } /*  439 */ else if (region.equals("57") == true) /*  440 */ {
                timezone = "Europe/Madrid";
            } /*  441 */ else if (region.equals("58") == true) /*  442 */ {
                timezone = "Europe/Madrid";
            } /*  443 */ else if (region.equals("59") == true) /*  444 */ {
                timezone = "Europe/Madrid";
            } /*  445 */ else if (region.equals("60") == true) /*  446 */ {
                timezone = "Europe/Madrid";
            }
        } /*  448 */ else if (country.equals("ET") == true) /*  449 */ {
            timezone = "Africa/Addis_Ababa";
        } /*  450 */ else if (country.equals("FI") == true) /*  451 */ {
            timezone = "Europe/Helsinki";
        } /*  452 */ else if (country.equals("FJ") == true) /*  453 */ {
            timezone = "Pacific/Fiji";
        } /*  454 */ else if (country.equals("FK") == true) /*  455 */ {
            timezone = "Atlantic/Stanley";
        } /*  456 */ else if (country.equals("FM") == true) /*  457 */ {
            timezone = "Pacific/Pohnpei";
        } /*  458 */ else if (country.equals("FO") == true) /*  459 */ {
            timezone = "Atlantic/Faroe";
        } /*  460 */ else if (country.equals("FR") == true) /*  461 */ {
            timezone = "Europe/Paris";
        } /*  462 */ else if (country.equals("GA") == true) /*  463 */ {
            timezone = "Africa/Libreville";
        } /*  464 */ else if (country.equals("GB") == true) /*  465 */ {
            timezone = "Europe/London";
        } /*  466 */ else if (country.equals("GD") == true) /*  467 */ {
            timezone = "America/Grenada";
        } /*  468 */ else if (country.equals("GE") == true) /*  469 */ {
            timezone = "Asia/Tbilisi";
        } /*  470 */ else if (country.equals("GF") == true) /*  471 */ {
            timezone = "America/Cayenne";
        } /*  472 */ else if (country.equals("GG") == true) /*  473 */ {
            timezone = "Europe/Guernsey";
        } /*  474 */ else if (country.equals("GH") == true) /*  475 */ {
            timezone = "Africa/Accra";
        } /*  476 */ else if (country.equals("GI") == true) /*  477 */ {
            timezone = "Europe/Gibraltar";
        } /*  478 */ else if (country.equals("GL") == true) {
            /*  479 */ if (region.equals("01") == true) /*  480 */ {
                timezone = "America/Thule";
            } /*  481 */ else if (region.equals("02") == true) /*  482 */ {
                timezone = "America/Scoresbysund";
            } /*  483 */ else if (region.equals("03") == true) /*  484 */ {
                timezone = "America/Godthab";
            }
        } /*  486 */ else if (country.equals("GM") == true) /*  487 */ {
            timezone = "Africa/Banjul";
        } /*  488 */ else if (country.equals("GN") == true) /*  489 */ {
            timezone = "Africa/Conakry";
        } /*  490 */ else if (country.equals("GP") == true) /*  491 */ {
            timezone = "America/Guadeloupe";
        } /*  492 */ else if (country.equals("GQ") == true) /*  493 */ {
            timezone = "Africa/Malabo";
        } /*  494 */ else if (country.equals("GR") == true) /*  495 */ {
            timezone = "Europe/Athens";
        } /*  496 */ else if (country.equals("GS") == true) /*  497 */ {
            timezone = "Atlantic/South_Georgia";
        } /*  498 */ else if (country.equals("GT") == true) /*  499 */ {
            timezone = "America/Guatemala";
        } /*  500 */ else if (country.equals("GU") == true) /*  501 */ {
            timezone = "Pacific/Guam";
        } /*  502 */ else if (country.equals("GW") == true) /*  503 */ {
            timezone = "Africa/Bissau";
        } /*  504 */ else if (country.equals("GY") == true) /*  505 */ {
            timezone = "America/Guyana";
        } /*  506 */ else if (country.equals("HK") == true) /*  507 */ {
            timezone = "Asia/Hong_Kong";
        } /*  508 */ else if (country.equals("HN") == true) /*  509 */ {
            timezone = "America/Tegucigalpa";
        } /*  510 */ else if (country.equals("HR") == true) /*  511 */ {
            timezone = "Europe/Zagreb";
        } /*  512 */ else if (country.equals("HT") == true) /*  513 */ {
            timezone = "America/Port-au-Prince";
        } /*  514 */ else if (country.equals("HU") == true) /*  515 */ {
            timezone = "Europe/Budapest";
        } /*  516 */ else if (country.equals("ID") == true) {
            /*  517 */ if (region.equals("01") == true) /*  518 */ {
                timezone = "Asia/Pontianak";
            } /*  519 */ else if (region.equals("02") == true) /*  520 */ {
                timezone = "Asia/Makassar";
            } /*  521 */ else if (region.equals("03") == true) /*  522 */ {
                timezone = "Asia/Jakarta";
            } /*  523 */ else if (region.equals("04") == true) /*  524 */ {
                timezone = "Asia/Jakarta";
            } /*  525 */ else if (region.equals("05") == true) /*  526 */ {
                timezone = "Asia/Jakarta";
            } /*  527 */ else if (region.equals("07") == true) /*  528 */ {
                timezone = "Asia/Jakarta";
            } /*  529 */ else if (region.equals("08") == true) /*  530 */ {
                timezone = "Asia/Jakarta";
            } /*  531 */ else if (region.equals("10") == true) /*  532 */ {
                timezone = "Asia/Jakarta";
            } /*  533 */ else if (region.equals("11") == true) /*  534 */ {
                timezone = "Asia/Pontianak";
            } /*  535 */ else if (region.equals("12") == true) /*  536 */ {
                timezone = "Asia/Makassar";
            } /*  537 */ else if (region.equals("13") == true) /*  538 */ {
                timezone = "Asia/Pontianak";
            } /*  539 */ else if (region.equals("14") == true) /*  540 */ {
                timezone = "Asia/Makassar";
            } /*  541 */ else if (region.equals("15") == true) /*  542 */ {
                timezone = "Asia/Jakarta";
            } /*  543 */ else if (region.equals("17") == true) /*  544 */ {
                timezone = "Asia/Makassar";
            } /*  545 */ else if (region.equals("18") == true) /*  546 */ {
                timezone = "Asia/Makassar";
            } /*  547 */ else if (region.equals("21") == true) /*  548 */ {
                timezone = "Asia/Makassar";
            } /*  549 */ else if (region.equals("22") == true) /*  550 */ {
                timezone = "Asia/Makassar";
            } /*  551 */ else if (region.equals("24") == true) /*  552 */ {
                timezone = "Asia/Jakarta";
            } /*  553 */ else if (region.equals("26") == true) /*  554 */ {
                timezone = "Asia/Pontianak";
            } /*  555 */ else if (region.equals("28") == true) /*  556 */ {
                timezone = "Asia/Jayapura";
            } /*  557 */ else if (region.equals("29") == true) /*  558 */ {
                timezone = "Asia/Makassar";
            } /*  559 */ else if (region.equals("30") == true) /*  560 */ {
                timezone = "Asia/Jakarta";
            } /*  561 */ else if (region.equals("31") == true) /*  562 */ {
                timezone = "Asia/Makassar";
            } /*  563 */ else if (region.equals("32") == true) /*  564 */ {
                timezone = "Asia/Jakarta";
            } /*  565 */ else if (region.equals("33") == true) /*  566 */ {
                timezone = "Asia/Jakarta";
            } /*  567 */ else if (region.equals("34") == true) /*  568 */ {
                timezone = "Asia/Makassar";
            } /*  569 */ else if (region.equals("35") == true) /*  570 */ {
                timezone = "Asia/Pontianak";
            } /*  571 */ else if (region.equals("36") == true) /*  572 */ {
                timezone = "Asia/Jayapura";
            } /*  573 */ else if (region.equals("37") == true) /*  574 */ {
                timezone = "Asia/Pontianak";
            } /*  575 */ else if (region.equals("38") == true) /*  576 */ {
                timezone = "Asia/Makassar";
            } /*  577 */ else if (region.equals("39") == true) /*  578 */ {
                timezone = "Asia/Jayapura";
            } /*  579 */ else if (region.equals("40") == true) /*  580 */ {
                timezone = "Asia/Pontianak";
            } /*  581 */ else if (region.equals("41") == true) /*  582 */ {
                timezone = "Asia/Makassar";
            }
        } /*  584 */ else if (country.equals("IE") == true) /*  585 */ {
            timezone = "Europe/Dublin";
        } /*  586 */ else if (country.equals("IL") == true) /*  587 */ {
            timezone = "Asia/Jerusalem";
        } /*  588 */ else if (country.equals("IM") == true) /*  589 */ {
            timezone = "Europe/Isle_of_Man";
        } /*  590 */ else if (country.equals("IN") == true) /*  591 */ {
            timezone = "Asia/Kolkata";
        } /*  592 */ else if (country.equals("IO") == true) /*  593 */ {
            timezone = "Indian/Chagos";
        } /*  594 */ else if (country.equals("IQ") == true) /*  595 */ {
            timezone = "Asia/Baghdad";
        } /*  596 */ else if (country.equals("IR") == true) /*  597 */ {
            timezone = "Asia/Tehran";
        } /*  598 */ else if (country.equals("IS") == true) /*  599 */ {
            timezone = "Atlantic/Reykjavik";
        } /*  600 */ else if (country.equals("IT") == true) /*  601 */ {
            timezone = "Europe/Rome";
        } /*  602 */ else if (country.equals("JE") == true) /*  603 */ {
            timezone = "Europe/Jersey";
        } /*  604 */ else if (country.equals("JM") == true) /*  605 */ {
            timezone = "America/Jamaica";
        } /*  606 */ else if (country.equals("JO") == true) /*  607 */ {
            timezone = "Asia/Amman";
        } /*  608 */ else if (country.equals("JP") == true) /*  609 */ {
            timezone = "Asia/Tokyo";
        } /*  610 */ else if (country.equals("KE") == true) /*  611 */ {
            timezone = "Africa/Nairobi";
        } /*  612 */ else if (country.equals("KG") == true) /*  613 */ {
            timezone = "Asia/Bishkek";
        } /*  614 */ else if (country.equals("KH") == true) /*  615 */ {
            timezone = "Asia/Phnom_Penh";
        } /*  616 */ else if (country.equals("KI") == true) /*  617 */ {
            timezone = "Pacific/Tarawa";
        } /*  618 */ else if (country.equals("KM") == true) /*  619 */ {
            timezone = "Indian/Comoro";
        } /*  620 */ else if (country.equals("KN") == true) /*  621 */ {
            timezone = "America/St_Kitts";
        } /*  622 */ else if (country.equals("KP") == true) /*  623 */ {
            timezone = "Asia/Pyongyang";
        } /*  624 */ else if (country.equals("KR") == true) /*  625 */ {
            timezone = "Asia/Seoul";
        } /*  626 */ else if (country.equals("KW") == true) /*  627 */ {
            timezone = "Asia/Kuwait";
        } /*  628 */ else if (country.equals("KY") == true) /*  629 */ {
            timezone = "America/Cayman";
        } /*  630 */ else if (country.equals("KZ") == true) {
            /*  631 */ if (region.equals("01") == true) /*  632 */ {
                timezone = "Asia/Almaty";
            } /*  633 */ else if (region.equals("02") == true) /*  634 */ {
                timezone = "Asia/Almaty";
            } /*  635 */ else if (region.equals("03") == true) /*  636 */ {
                timezone = "Asia/Qyzylorda";
            } /*  637 */ else if (region.equals("04") == true) /*  638 */ {
                timezone = "Asia/Aqtobe";
            } /*  639 */ else if (region.equals("05") == true) /*  640 */ {
                timezone = "Asia/Qyzylorda";
            } /*  641 */ else if (region.equals("06") == true) /*  642 */ {
                timezone = "Asia/Aqtau";
            } /*  643 */ else if (region.equals("07") == true) /*  644 */ {
                timezone = "Asia/Oral";
            } /*  645 */ else if (region.equals("08") == true) /*  646 */ {
                timezone = "Asia/Qyzylorda";
            } /*  647 */ else if (region.equals("09") == true) /*  648 */ {
                timezone = "Asia/Aqtau";
            } /*  649 */ else if (region.equals("10") == true) /*  650 */ {
                timezone = "Asia/Qyzylorda";
            } /*  651 */ else if (region.equals("11") == true) /*  652 */ {
                timezone = "Asia/Almaty";
            } /*  653 */ else if (region.equals("12") == true) /*  654 */ {
                timezone = "Asia/Almaty";
            } /*  655 */ else if (region.equals("13") == true) /*  656 */ {
                timezone = "Asia/Aqtobe";
            } /*  657 */ else if (region.equals("14") == true) /*  658 */ {
                timezone = "Asia/Qyzylorda";
            } /*  659 */ else if (region.equals("15") == true) /*  660 */ {
                timezone = "Asia/Almaty";
            } /*  661 */ else if (region.equals("16") == true) /*  662 */ {
                timezone = "Asia/Aqtobe";
            } /*  663 */ else if (region.equals("17") == true) /*  664 */ {
                timezone = "Asia/Almaty";
            }
        } /*  666 */ else if (country.equals("LA") == true) /*  667 */ {
            timezone = "Asia/Vientiane";
        } /*  668 */ else if (country.equals("LB") == true) /*  669 */ {
            timezone = "Asia/Beirut";
        } /*  670 */ else if (country.equals("LC") == true) /*  671 */ {
            timezone = "America/St_Lucia";
        } /*  672 */ else if (country.equals("LI") == true) /*  673 */ {
            timezone = "Europe/Vaduz";
        } /*  674 */ else if (country.equals("LK") == true) /*  675 */ {
            timezone = "Asia/Colombo";
        } /*  676 */ else if (country.equals("LR") == true) /*  677 */ {
            timezone = "Africa/Monrovia";
        } /*  678 */ else if (country.equals("LS") == true) /*  679 */ {
            timezone = "Africa/Maseru";
        } /*  680 */ else if (country.equals("LT") == true) /*  681 */ {
            timezone = "Europe/Vilnius";
        } /*  682 */ else if (country.equals("LU") == true) /*  683 */ {
            timezone = "Europe/Luxembourg";
        } /*  684 */ else if (country.equals("LV") == true) /*  685 */ {
            timezone = "Europe/Riga";
        } /*  686 */ else if (country.equals("LY") == true) /*  687 */ {
            timezone = "Africa/Tripoli";
        } /*  688 */ else if (country.equals("MA") == true) /*  689 */ {
            timezone = "Africa/Casablanca";
        } /*  690 */ else if (country.equals("MC") == true) /*  691 */ {
            timezone = "Europe/Monaco";
        } /*  692 */ else if (country.equals("MD") == true) /*  693 */ {
            timezone = "Europe/Chisinau";
        } /*  694 */ else if (country.equals("ME") == true) /*  695 */ {
            timezone = "Europe/Podgorica";
        } /*  696 */ else if (country.equals("MG") == true) /*  697 */ {
            timezone = "Indian/Antananarivo";
        } /*  698 */ else if (country.equals("MH") == true) /*  699 */ {
            timezone = "Pacific/Kwajalein";
        } /*  700 */ else if (country.equals("MK") == true) /*  701 */ {
            timezone = "Europe/Skopje";
        } /*  702 */ else if (country.equals("ML") == true) /*  703 */ {
            timezone = "Africa/Bamako";
        } /*  704 */ else if (country.equals("MM") == true) /*  705 */ {
            timezone = "Asia/Rangoon";
        } /*  706 */ else if (country.equals("MN") == true) {
            /*  707 */ if (region.equals("06") == true) /*  708 */ {
                timezone = "Asia/Choibalsan";
            } /*  709 */ else if (region.equals("11") == true) /*  710 */ {
                timezone = "Asia/Ulaanbaatar";
            } /*  711 */ else if (region.equals("17") == true) /*  712 */ {
                timezone = "Asia/Choibalsan";
            } /*  713 */ else if (region.equals("19") == true) /*  714 */ {
                timezone = "Asia/Hovd";
            } /*  715 */ else if (region.equals("20") == true) /*  716 */ {
                timezone = "Asia/Ulaanbaatar";
            } /*  717 */ else if (region.equals("21") == true) /*  718 */ {
                timezone = "Asia/Ulaanbaatar";
            } /*  719 */ else if (region.equals("25") == true) /*  720 */ {
                timezone = "Asia/Ulaanbaatar";
            }
        } /*  722 */ else if (country.equals("MO") == true) /*  723 */ {
            timezone = "Asia/Macau";
        } /*  724 */ else if (country.equals("MP") == true) /*  725 */ {
            timezone = "Pacific/Saipan";
        } /*  726 */ else if (country.equals("MQ") == true) /*  727 */ {
            timezone = "America/Martinique";
        } /*  728 */ else if (country.equals("MR") == true) /*  729 */ {
            timezone = "Africa/Nouakchott";
        } /*  730 */ else if (country.equals("MS") == true) /*  731 */ {
            timezone = "America/Montserrat";
        } /*  732 */ else if (country.equals("MT") == true) /*  733 */ {
            timezone = "Europe/Malta";
        } /*  734 */ else if (country.equals("MU") == true) /*  735 */ {
            timezone = "Indian/Mauritius";
        } /*  736 */ else if (country.equals("MV") == true) /*  737 */ {
            timezone = "Indian/Maldives";
        } /*  738 */ else if (country.equals("MW") == true) /*  739 */ {
            timezone = "Africa/Blantyre";
        } /*  740 */ else if (country.equals("MX") == true) {
            /*  741 */ if (region.equals("01") == true) /*  742 */ {
                timezone = "America/Bahia_Banderas";
            } /*  743 */ else if (region.equals("02") == true) /*  744 */ {
                timezone = "America/Tijuana";
            } /*  745 */ else if (region.equals("03") == true) /*  746 */ {
                timezone = "America/Mazatlan";
            } /*  747 */ else if (region.equals("04") == true) /*  748 */ {
                timezone = "America/Merida";
            } /*  749 */ else if (region.equals("05") == true) /*  750 */ {
                timezone = "America/Merida";
            } /*  751 */ else if (region.equals("06") == true) /*  752 */ {
                timezone = "America/Chihuahua";
            } /*  753 */ else if (region.equals("07") == true) /*  754 */ {
                timezone = "America/Monterrey";
            } /*  755 */ else if (region.equals("08") == true) /*  756 */ {
                timezone = "America/Bahia_Banderas";
            } /*  757 */ else if (region.equals("09") == true) /*  758 */ {
                timezone = "America/Mexico_City";
            } /*  759 */ else if (region.equals("10") == true) /*  760 */ {
                timezone = "America/Mazatlan";
            } /*  761 */ else if (region.equals("11") == true) /*  762 */ {
                timezone = "America/Mexico_City";
            } /*  763 */ else if (region.equals("12") == true) /*  764 */ {
                timezone = "America/Mexico_City";
            } /*  765 */ else if (region.equals("13") == true) /*  766 */ {
                timezone = "America/Mexico_City";
            } /*  767 */ else if (region.equals("14") == true) /*  768 */ {
                timezone = "America/Bahia_Banderas";
            } /*  769 */ else if (region.equals("15") == true) /*  770 */ {
                timezone = "America/Mexico_City";
            } /*  771 */ else if (region.equals("16") == true) /*  772 */ {
                timezone = "America/Mexico_City";
            } /*  773 */ else if (region.equals("17") == true) /*  774 */ {
                timezone = "America/Mexico_City";
            } /*  775 */ else if (region.equals("18") == true) /*  776 */ {
                timezone = "America/Bahia_Banderas";
            } /*  777 */ else if (region.equals("19") == true) /*  778 */ {
                timezone = "America/Monterrey";
            } /*  779 */ else if (region.equals("20") == true) /*  780 */ {
                timezone = "America/Mexico_City";
            } /*  781 */ else if (region.equals("21") == true) /*  782 */ {
                timezone = "America/Mexico_City";
            } /*  783 */ else if (region.equals("22") == true) /*  784 */ {
                timezone = "America/Mexico_City";
            } /*  785 */ else if (region.equals("23") == true) /*  786 */ {
                timezone = "America/Cancun";
            } /*  787 */ else if (region.equals("24") == true) /*  788 */ {
                timezone = "America/Mexico_City";
            } /*  789 */ else if (region.equals("25") == true) /*  790 */ {
                timezone = "America/Mazatlan";
            } /*  791 */ else if (region.equals("26") == true) /*  792 */ {
                timezone = "America/Hermosillo";
            } /*  793 */ else if (region.equals("27") == true) /*  794 */ {
                timezone = "America/Merida";
            } /*  795 */ else if (region.equals("28") == true) /*  796 */ {
                timezone = "America/Matamoros";
            } /*  797 */ else if (region.equals("29") == true) /*  798 */ {
                timezone = "America/Mexico_City";
            } /*  799 */ else if (region.equals("30") == true) /*  800 */ {
                timezone = "America/Mexico_City";
            } /*  801 */ else if (region.equals("31") == true) /*  802 */ {
                timezone = "America/Merida";
            } /*  803 */ else if (region.equals("32") == true) /*  804 */ {
                timezone = "America/Bahia_Banderas";
            }
        } /*  806 */ else if (country.equals("MY") == true) {
            /*  807 */ if (region.equals("01") == true) /*  808 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  809 */ else if (region.equals("02") == true) /*  810 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  811 */ else if (region.equals("03") == true) /*  812 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  813 */ else if (region.equals("04") == true) /*  814 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  815 */ else if (region.equals("05") == true) /*  816 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  817 */ else if (region.equals("06") == true) /*  818 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  819 */ else if (region.equals("07") == true) /*  820 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  821 */ else if (region.equals("08") == true) /*  822 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  823 */ else if (region.equals("09") == true) /*  824 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  825 */ else if (region.equals("11") == true) /*  826 */ {
                timezone = "Asia/Kuching";
            } /*  827 */ else if (region.equals("12") == true) /*  828 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  829 */ else if (region.equals("13") == true) /*  830 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  831 */ else if (region.equals("14") == true) /*  832 */ {
                timezone = "Asia/Kuala_Lumpur";
            } /*  833 */ else if (region.equals("15") == true) /*  834 */ {
                timezone = "Asia/Kuching";
            } /*  835 */ else if (region.equals("16") == true) /*  836 */ {
                timezone = "Asia/Kuching";
            }
        } /*  838 */ else if (country.equals("MZ") == true) /*  839 */ {
            timezone = "Africa/Maputo";
        } /*  840 */ else if (country.equals("NA") == true) /*  841 */ {
            timezone = "Africa/Windhoek";
        } /*  842 */ else if (country.equals("NC") == true) /*  843 */ {
            timezone = "Pacific/Noumea";
        } /*  844 */ else if (country.equals("NE") == true) /*  845 */ {
            timezone = "Africa/Niamey";
        } /*  846 */ else if (country.equals("NF") == true) /*  847 */ {
            timezone = "Pacific/Norfolk";
        } /*  848 */ else if (country.equals("NG") == true) /*  849 */ {
            timezone = "Africa/Lagos";
        } /*  850 */ else if (country.equals("NI") == true) /*  851 */ {
            timezone = "America/Managua";
        } /*  852 */ else if (country.equals("NL") == true) /*  853 */ {
            timezone = "Europe/Amsterdam";
        } /*  854 */ else if (country.equals("NO") == true) /*  855 */ {
            timezone = "Europe/Oslo";
        } /*  856 */ else if (country.equals("NP") == true) /*  857 */ {
            timezone = "Asia/Kathmandu";
        } /*  858 */ else if (country.equals("NR") == true) /*  859 */ {
            timezone = "Pacific/Nauru";
        } /*  860 */ else if (country.equals("NU") == true) /*  861 */ {
            timezone = "Pacific/Niue";
        } /*  862 */ else if (country.equals("NZ") == true) {
            /*  863 */ if (region.equals("E7") == true) /*  864 */ {
                timezone = "Pacific/Auckland";
            } /*  865 */ else if (region.equals("E8") == true) /*  866 */ {
                timezone = "Pacific/Auckland";
            } /*  867 */ else if (region.equals("E9") == true) /*  868 */ {
                timezone = "Pacific/Auckland";
            } /*  869 */ else if (region.equals("F1") == true) /*  870 */ {
                timezone = "Pacific/Auckland";
            } /*  871 */ else if (region.equals("F2") == true) /*  872 */ {
                timezone = "Pacific/Auckland";
            } /*  873 */ else if (region.equals("F3") == true) /*  874 */ {
                timezone = "Pacific/Auckland";
            } /*  875 */ else if (region.equals("F4") == true) /*  876 */ {
                timezone = "Pacific/Auckland";
            } /*  877 */ else if (region.equals("F5") == true) /*  878 */ {
                timezone = "Pacific/Auckland";
            } /*  879 */ else if (region.equals("F6") == true) /*  880 */ {
                timezone = "Pacific/Auckland";
            } /*  881 */ else if (region.equals("F7") == true) /*  882 */ {
                timezone = "Pacific/Chatham";
            } /*  883 */ else if (region.equals("F8") == true) /*  884 */ {
                timezone = "Pacific/Auckland";
            } /*  885 */ else if (region.equals("F9") == true) /*  886 */ {
                timezone = "Pacific/Auckland";
            } /*  887 */ else if (region.equals("G1") == true) /*  888 */ {
                timezone = "Pacific/Auckland";
            } /*  889 */ else if (region.equals("G2") == true) /*  890 */ {
                timezone = "Pacific/Auckland";
            } /*  891 */ else if (region.equals("G3") == true) /*  892 */ {
                timezone = "Pacific/Auckland";
            }
        } /*  894 */ else if (country.equals("OM") == true) /*  895 */ {
            timezone = "Asia/Muscat";
        } /*  896 */ else if (country.equals("PA") == true) /*  897 */ {
            timezone = "America/Panama";
        } /*  898 */ else if (country.equals("PE") == true) /*  899 */ {
            timezone = "America/Lima";
        } /*  900 */ else if (country.equals("PF") == true) /*  901 */ {
            timezone = "Pacific/Marquesas";
        } /*  902 */ else if (country.equals("PG") == true) /*  903 */ {
            timezone = "Pacific/Port_Moresby";
        } /*  904 */ else if (country.equals("PH") == true) /*  905 */ {
            timezone = "Asia/Manila";
        } /*  906 */ else if (country.equals("PK") == true) /*  907 */ {
            timezone = "Asia/Karachi";
        } /*  908 */ else if (country.equals("PL") == true) /*  909 */ {
            timezone = "Europe/Warsaw";
        } /*  910 */ else if (country.equals("PM") == true) /*  911 */ {
            timezone = "America/Miquelon";
        } /*  912 */ else if (country.equals("PR") == true) /*  913 */ {
            timezone = "America/Puerto_Rico";
        } /*  914 */ else if (country.equals("PS") == true) /*  915 */ {
            timezone = "Asia/Gaza";
        } /*  916 */ else if (country.equals("PT") == true) {
            /*  917 */ if (region.equals("02") == true) /*  918 */ {
                timezone = "Europe/Lisbon";
            } /*  919 */ else if (region.equals("03") == true) /*  920 */ {
                timezone = "Europe/Lisbon";
            } /*  921 */ else if (region.equals("04") == true) /*  922 */ {
                timezone = "Europe/Lisbon";
            } /*  923 */ else if (region.equals("05") == true) /*  924 */ {
                timezone = "Europe/Lisbon";
            } /*  925 */ else if (region.equals("06") == true) /*  926 */ {
                timezone = "Europe/Lisbon";
            } /*  927 */ else if (region.equals("07") == true) /*  928 */ {
                timezone = "Europe/Lisbon";
            } /*  929 */ else if (region.equals("08") == true) /*  930 */ {
                timezone = "Europe/Lisbon";
            } /*  931 */ else if (region.equals("09") == true) /*  932 */ {
                timezone = "Europe/Lisbon";
            } /*  933 */ else if (region.equals("10") == true) /*  934 */ {
                timezone = "Atlantic/Madeira";
            } /*  935 */ else if (region.equals("11") == true) /*  936 */ {
                timezone = "Europe/Lisbon";
            } /*  937 */ else if (region.equals("13") == true) /*  938 */ {
                timezone = "Europe/Lisbon";
            } /*  939 */ else if (region.equals("14") == true) /*  940 */ {
                timezone = "Europe/Lisbon";
            } /*  941 */ else if (region.equals("16") == true) /*  942 */ {
                timezone = "Europe/Lisbon";
            } /*  943 */ else if (region.equals("17") == true) /*  944 */ {
                timezone = "Europe/Lisbon";
            } /*  945 */ else if (region.equals("18") == true) /*  946 */ {
                timezone = "Europe/Lisbon";
            } /*  947 */ else if (region.equals("19") == true) /*  948 */ {
                timezone = "Europe/Lisbon";
            } /*  949 */ else if (region.equals("20") == true) /*  950 */ {
                timezone = "Europe/Lisbon";
            } /*  951 */ else if (region.equals("21") == true) /*  952 */ {
                timezone = "Europe/Lisbon";
            } /*  953 */ else if (region.equals("22") == true) /*  954 */ {
                timezone = "Europe/Lisbon";
            } /*  955 */ else if (region.equals("23") == true) /*  956 */ {
                timezone = "Atlantic/Azores";
            }
        } /*  958 */ else if (country.equals("PW") == true) /*  959 */ {
            timezone = "Pacific/Palau";
        } /*  960 */ else if (country.equals("PY") == true) /*  961 */ {
            timezone = "America/Asuncion";
        } /*  962 */ else if (country.equals("QA") == true) /*  963 */ {
            timezone = "Asia/Qatar";
        } /*  964 */ else if (country.equals("RE") == true) /*  965 */ {
            timezone = "Indian/Reunion";
        } /*  966 */ else if (country.equals("RO") == true) /*  967 */ {
            timezone = "Europe/Bucharest";
        } /*  968 */ else if (country.equals("RS") == true) /*  969 */ {
            timezone = "Europe/Belgrade";
        } /*  970 */ else if (country.equals("RU") == true) {
            /*  971 */ if (region.equals("01") == true) /*  972 */ {
                timezone = "Europe/Volgograd";
            } /*  973 */ else if (region.equals("02") == true) /*  974 */ {
                timezone = "Asia/Irkutsk";
            } /*  975 */ else if (region.equals("03") == true) /*  976 */ {
                timezone = "Asia/Novokuznetsk";
            } /*  977 */ else if (region.equals("04") == true) /*  978 */ {
                timezone = "Asia/Novosibirsk";
            } /*  979 */ else if (region.equals("05") == true) /*  980 */ {
                timezone = "Asia/Vladivostok";
            } /*  981 */ else if (region.equals("06") == true) /*  982 */ {
                timezone = "Europe/Moscow";
            } /*  983 */ else if (region.equals("07") == true) /*  984 */ {
                timezone = "Europe/Volgograd";
            } /*  985 */ else if (region.equals("08") == true) /*  986 */ {
                timezone = "Europe/Samara";
            } /*  987 */ else if (region.equals("09") == true) /*  988 */ {
                timezone = "Europe/Volgograd";
            } /*  989 */ else if (region.equals("10") == true) /*  990 */ {
                timezone = "Europe/Moscow";
            } /*  991 */ else if (region.equals("11") == true) /*  992 */ {
                timezone = "Asia/Irkutsk";
            } /*  993 */ else if (region.equals("12") == true) /*  994 */ {
                timezone = "Europe/Volgograd";
            } /*  995 */ else if (region.equals("13") == true) /*  996 */ {
                timezone = "Asia/Yekaterinburg";
            } /*  997 */ else if (region.equals("14") == true) /*  998 */ {
                timezone = "Asia/Irkutsk";
            } /*  999 */ else if (region.equals("15") == true) /* 1000 */ {
                timezone = "Asia/Anadyr";
            } /* 1001 */ else if (region.equals("16") == true) /* 1002 */ {
                timezone = "Europe/Samara";
            } /* 1003 */ else if (region.equals("17") == true) /* 1004 */ {
                timezone = "Europe/Volgograd";
            } /* 1005 */ else if (region.equals("18") == true) /* 1006 */ {
                timezone = "Asia/Krasnoyarsk";
            } /* 1007 */ else if (region.equals("20") == true) /* 1008 */ {
                timezone = "Asia/Irkutsk";
            } /* 1009 */ else if (region.equals("21") == true) /* 1010 */ {
                timezone = "Europe/Moscow";
            } /* 1011 */ else if (region.equals("22") == true) /* 1012 */ {
                timezone = "Europe/Volgograd";
            } /* 1013 */ else if (region.equals("23") == true) /* 1014 */ {
                timezone = "Europe/Kaliningrad";
            } /* 1015 */ else if (region.equals("24") == true) /* 1016 */ {
                timezone = "Europe/Volgograd";
            } /* 1017 */ else if (region.equals("25") == true) /* 1018 */ {
                timezone = "Europe/Moscow";
            } /* 1019 */ else if (region.equals("26") == true) /* 1020 */ {
                timezone = "Asia/Kamchatka";
            } /* 1021 */ else if (region.equals("27") == true) /* 1022 */ {
                timezone = "Europe/Volgograd";
            } /* 1023 */ else if (region.equals("28") == true) /* 1024 */ {
                timezone = "Europe/Moscow";
            } /* 1025 */ else if (region.equals("29") == true) /* 1026 */ {
                timezone = "Asia/Novokuznetsk";
            } /* 1027 */ else if (region.equals("30") == true) /* 1028 */ {
                timezone = "Asia/Sakhalin";
            } /* 1029 */ else if (region.equals("31") == true) /* 1030 */ {
                timezone = "Asia/Krasnoyarsk";
            } /* 1031 */ else if (region.equals("32") == true) /* 1032 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1033 */ else if (region.equals("33") == true) /* 1034 */ {
                timezone = "Europe/Samara";
            } /* 1035 */ else if (region.equals("34") == true) /* 1036 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1037 */ else if (region.equals("36") == true) /* 1038 */ {
                timezone = "Asia/Anadyr";
            } /* 1039 */ else if (region.equals("37") == true) /* 1040 */ {
                timezone = "Europe/Moscow";
            } /* 1041 */ else if (region.equals("38") == true) /* 1042 */ {
                timezone = "Europe/Volgograd";
            } /* 1043 */ else if (region.equals("39") == true) /* 1044 */ {
                timezone = "Asia/Krasnoyarsk";
            } /* 1045 */ else if (region.equals("40") == true) /* 1046 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1047 */ else if (region.equals("41") == true) /* 1048 */ {
                timezone = "Europe/Moscow";
            } /* 1049 */ else if (region.equals("42") == true) /* 1050 */ {
                timezone = "Europe/Moscow";
            } /* 1051 */ else if (region.equals("43") == true) /* 1052 */ {
                timezone = "Europe/Moscow";
            } /* 1053 */ else if (region.equals("44") == true) /* 1054 */ {
                timezone = "Asia/Magadan";
            } /* 1055 */ else if (region.equals("45") == true) /* 1056 */ {
                timezone = "Europe/Samara";
            } /* 1057 */ else if (region.equals("46") == true) /* 1058 */ {
                timezone = "Europe/Samara";
            } /* 1059 */ else if (region.equals("47") == true) /* 1060 */ {
                timezone = "Europe/Moscow";
            } /* 1061 */ else if (region.equals("48") == true) /* 1062 */ {
                timezone = "Europe/Moscow";
            } /* 1063 */ else if (region.equals("49") == true) /* 1064 */ {
                timezone = "Europe/Moscow";
            } /* 1065 */ else if (region.equals("50") == true) /* 1066 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1067 */ else if (region.equals("51") == true) /* 1068 */ {
                timezone = "Europe/Moscow";
            } /* 1069 */ else if (region.equals("52") == true) /* 1070 */ {
                timezone = "Europe/Moscow";
            } /* 1071 */ else if (region.equals("53") == true) /* 1072 */ {
                timezone = "Asia/Novosibirsk";
            } /* 1073 */ else if (region.equals("54") == true) /* 1074 */ {
                timezone = "Asia/Omsk";
            } /* 1075 */ else if (region.equals("55") == true) /* 1076 */ {
                timezone = "Europe/Samara";
            } /* 1077 */ else if (region.equals("56") == true) /* 1078 */ {
                timezone = "Europe/Moscow";
            } /* 1079 */ else if (region.equals("57") == true) /* 1080 */ {
                timezone = "Europe/Samara";
            } /* 1081 */ else if (region.equals("58") == true) /* 1082 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1083 */ else if (region.equals("59") == true) /* 1084 */ {
                timezone = "Asia/Vladivostok";
            } /* 1085 */ else if (region.equals("60") == true) /* 1086 */ {
                timezone = "Europe/Moscow";
            } /* 1087 */ else if (region.equals("61") == true) /* 1088 */ {
                timezone = "Europe/Volgograd";
            } /* 1089 */ else if (region.equals("62") == true) /* 1090 */ {
                timezone = "Europe/Moscow";
            } /* 1091 */ else if (region.equals("63") == true) /* 1092 */ {
                timezone = "Asia/Yakutsk";
            } /* 1093 */ else if (region.equals("64") == true) /* 1094 */ {
                timezone = "Asia/Sakhalin";
            } /* 1095 */ else if (region.equals("65") == true) /* 1096 */ {
                timezone = "Europe/Samara";
            } /* 1097 */ else if (region.equals("66") == true) /* 1098 */ {
                timezone = "Europe/Moscow";
            } /* 1099 */ else if (region.equals("67") == true) /* 1100 */ {
                timezone = "Europe/Samara";
            } /* 1101 */ else if (region.equals("68") == true) /* 1102 */ {
                timezone = "Europe/Volgograd";
            } /* 1103 */ else if (region.equals("69") == true) /* 1104 */ {
                timezone = "Europe/Moscow";
            } /* 1105 */ else if (region.equals("70") == true) /* 1106 */ {
                timezone = "Europe/Volgograd";
            } /* 1107 */ else if (region.equals("71") == true) /* 1108 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1109 */ else if (region.equals("72") == true) /* 1110 */ {
                timezone = "Europe/Moscow";
            } /* 1111 */ else if (region.equals("73") == true) /* 1112 */ {
                timezone = "Europe/Samara";
            } /* 1113 */ else if (region.equals("74") == true) /* 1114 */ {
                timezone = "Asia/Yakutsk";
            } /* 1115 */ else if (region.equals("75") == true) /* 1116 */ {
                timezone = "Asia/Novosibirsk";
            } /* 1117 */ else if (region.equals("76") == true) /* 1118 */ {
                timezone = "Europe/Moscow";
            } /* 1119 */ else if (region.equals("77") == true) /* 1120 */ {
                timezone = "Europe/Moscow";
            } /* 1121 */ else if (region.equals("78") == true) /* 1122 */ {
                timezone = "Asia/Omsk";
            } /* 1123 */ else if (region.equals("79") == true) /* 1124 */ {
                timezone = "Asia/Irkutsk";
            } /* 1125 */ else if (region.equals("80") == true) /* 1126 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1127 */ else if (region.equals("81") == true) /* 1128 */ {
                timezone = "Europe/Samara";
            } /* 1129 */ else if (region.equals("83") == true) /* 1130 */ {
                timezone = "Europe/Moscow";
            } /* 1131 */ else if (region.equals("84") == true) /* 1132 */ {
                timezone = "Europe/Volgograd";
            } /* 1133 */ else if (region.equals("85") == true) /* 1134 */ {
                timezone = "Europe/Moscow";
            } /* 1135 */ else if (region.equals("86") == true) /* 1136 */ {
                timezone = "Europe/Volgograd";
            } /* 1137 */ else if (region.equals("87") == true) /* 1138 */ {
                timezone = "Asia/Omsk";
            } /* 1139 */ else if (region.equals("88") == true) /* 1140 */ {
                timezone = "Europe/Moscow";
            } /* 1141 */ else if (region.equals("89") == true) /* 1142 */ {
                timezone = "Asia/Vladivostok";
            } /* 1143 */ else if (region.equals("90") == true) /* 1144 */ {
                timezone = "Asia/Yekaterinburg";
            } /* 1145 */ else if (region.equals("91") == true) /* 1146 */ {
                timezone = "Asia/Krasnoyarsk";
            } /* 1147 */ else if (region.equals("92") == true) /* 1148 */ {
                timezone = "Asia/Anadyr";
            } /* 1149 */ else if (region.equals("93") == true) /* 1150 */ {
                timezone = "Asia/Irkutsk";
            } /* 1151 */ else if (region.equals("CI") == true) /* 1152 */ {
                timezone = "Europe/Volgograd";
            } /* 1153 */ else if (region.equals("JA") == true) /* 1154 */ {
                timezone = "Asia/Sakhalin";
            }
        } /* 1156 */ else if (country.equals("RW") == true) /* 1157 */ {
            timezone = "Africa/Kigali";
        } /* 1158 */ else if (country.equals("SA") == true) /* 1159 */ {
            timezone = "Asia/Riyadh";
        } /* 1160 */ else if (country.equals("SB") == true) /* 1161 */ {
            timezone = "Pacific/Guadalcanal";
        } /* 1162 */ else if (country.equals("SC") == true) /* 1163 */ {
            timezone = "Indian/Mahe";
        } /* 1164 */ else if (country.equals("SD") == true) /* 1165 */ {
            timezone = "Africa/Khartoum";
        } /* 1166 */ else if (country.equals("SE") == true) /* 1167 */ {
            timezone = "Europe/Stockholm";
        } /* 1168 */ else if (country.equals("SG") == true) /* 1169 */ {
            timezone = "Asia/Singapore";
        } /* 1170 */ else if (country.equals("SH") == true) /* 1171 */ {
            timezone = "Atlantic/St_Helena";
        } /* 1172 */ else if (country.equals("SI") == true) /* 1173 */ {
            timezone = "Europe/Ljubljana";
        } /* 1174 */ else if (country.equals("SJ") == true) /* 1175 */ {
            timezone = "Arctic/Longyearbyen";
        } /* 1176 */ else if (country.equals("SK") == true) /* 1177 */ {
            timezone = "Europe/Bratislava";
        } /* 1178 */ else if (country.equals("SL") == true) /* 1179 */ {
            timezone = "Africa/Freetown";
        } /* 1180 */ else if (country.equals("SM") == true) /* 1181 */ {
            timezone = "Europe/San_Marino";
        } /* 1182 */ else if (country.equals("SN") == true) /* 1183 */ {
            timezone = "Africa/Dakar";
        } /* 1184 */ else if (country.equals("SO") == true) /* 1185 */ {
            timezone = "Africa/Mogadishu";
        } /* 1186 */ else if (country.equals("SR") == true) /* 1187 */ {
            timezone = "America/Paramaribo";
        } /* 1188 */ else if (country.equals("ST") == true) /* 1189 */ {
            timezone = "Africa/Sao_Tome";
        } /* 1190 */ else if (country.equals("SV") == true) /* 1191 */ {
            timezone = "America/El_Salvador";
        } /* 1192 */ else if (country.equals("SY") == true) /* 1193 */ {
            timezone = "Asia/Damascus";
        } /* 1194 */ else if (country.equals("SZ") == true) /* 1195 */ {
            timezone = "Africa/Mbabane";
        } /* 1196 */ else if (country.equals("TC") == true) /* 1197 */ {
            timezone = "America/Grand_Turk";
        } /* 1198 */ else if (country.equals("TD") == true) /* 1199 */ {
            timezone = "Africa/Ndjamena";
        } /* 1200 */ else if (country.equals("TF") == true) /* 1201 */ {
            timezone = "Indian/Kerguelen";
        } /* 1202 */ else if (country.equals("TG") == true) /* 1203 */ {
            timezone = "Africa/Lome";
        } /* 1204 */ else if (country.equals("TH") == true) /* 1205 */ {
            timezone = "Asia/Bangkok";
        } /* 1206 */ else if (country.equals("TJ") == true) /* 1207 */ {
            timezone = "Asia/Dushanbe";
        } /* 1208 */ else if (country.equals("TK") == true) /* 1209 */ {
            timezone = "Pacific/Fakaofo";
        } /* 1210 */ else if (country.equals("TL") == true) /* 1211 */ {
            timezone = "Asia/Dili";
        } /* 1212 */ else if (country.equals("TM") == true) /* 1213 */ {
            timezone = "Asia/Ashgabat";
        } /* 1214 */ else if (country.equals("TN") == true) /* 1215 */ {
            timezone = "Africa/Tunis";
        } /* 1216 */ else if (country.equals("TO") == true) /* 1217 */ {
            timezone = "Pacific/Tongatapu";
        } /* 1218 */ else if (country.equals("TR") == true) /* 1219 */ {
            timezone = "Europe/Istanbul";
        } /* 1220 */ else if (country.equals("TT") == true) /* 1221 */ {
            timezone = "America/Port_of_Spain";
        } /* 1222 */ else if (country.equals("TV") == true) /* 1223 */ {
            timezone = "Pacific/Funafuti";
        } /* 1224 */ else if (country.equals("TW") == true) /* 1225 */ {
            timezone = "Asia/Taipei";
        } /* 1226 */ else if (country.equals("TZ") == true) /* 1227 */ {
            timezone = "Africa/Dar_es_Salaam";
        } /* 1228 */ else if (country.equals("UA") == true) {
            /* 1229 */ if (region.equals("01") == true) /* 1230 */ {
                timezone = "Europe/Kiev";
            } /* 1231 */ else if (region.equals("02") == true) /* 1232 */ {
                timezone = "Europe/Kiev";
            } /* 1233 */ else if (region.equals("03") == true) /* 1234 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1235 */ else if (region.equals("04") == true) /* 1236 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1237 */ else if (region.equals("05") == true) /* 1238 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1239 */ else if (region.equals("06") == true) /* 1240 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1241 */ else if (region.equals("07") == true) /* 1242 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1243 */ else if (region.equals("08") == true) /* 1244 */ {
                timezone = "Europe/Simferopol";
            } /* 1245 */ else if (region.equals("09") == true) /* 1246 */ {
                timezone = "Europe/Kiev";
            } /* 1247 */ else if (region.equals("10") == true) /* 1248 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1249 */ else if (region.equals("11") == true) /* 1250 */ {
                timezone = "Europe/Simferopol";
            } /* 1251 */ else if (region.equals("12") == true) /* 1252 */ {
                timezone = "Europe/Kiev";
            } /* 1253 */ else if (region.equals("13") == true) /* 1254 */ {
                timezone = "Europe/Kiev";
            } /* 1255 */ else if (region.equals("14") == true) /* 1256 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1257 */ else if (region.equals("15") == true) /* 1258 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1259 */ else if (region.equals("16") == true) /* 1260 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1261 */ else if (region.equals("17") == true) /* 1262 */ {
                timezone = "Europe/Simferopol";
            } /* 1263 */ else if (region.equals("18") == true) /* 1264 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1265 */ else if (region.equals("19") == true) /* 1266 */ {
                timezone = "Europe/Kiev";
            } /* 1267 */ else if (region.equals("20") == true) /* 1268 */ {
                timezone = "Europe/Simferopol";
            } /* 1269 */ else if (region.equals("21") == true) /* 1270 */ {
                timezone = "Europe/Kiev";
            } /* 1271 */ else if (region.equals("22") == true) /* 1272 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1273 */ else if (region.equals("23") == true) /* 1274 */ {
                timezone = "Europe/Kiev";
            } /* 1275 */ else if (region.equals("24") == true) /* 1276 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1277 */ else if (region.equals("25") == true) /* 1278 */ {
                timezone = "Europe/Uzhgorod";
            } /* 1279 */ else if (region.equals("26") == true) /* 1280 */ {
                timezone = "Europe/Zaporozhye";
            } /* 1281 */ else if (region.equals("27") == true) /* 1282 */ {
                timezone = "Europe/Kiev";
            }
        } /* 1284 */ else if (country.equals("UG") == true) /* 1285 */ {
            timezone = "Africa/Kampala";
        } /* 1286 */ else if (country.equals("UM") == true) /* 1287 */ {
            timezone = "Pacific/Wake";
        } /* 1288 */ else if (country.equals("US") == true) {
            /* 1289 */ if (region.equals("AK") == true) /* 1290 */ {
                timezone = "America/Anchorage";
            } /* 1291 */ else if (region.equals("AL") == true) /* 1292 */ {
                timezone = "America/Chicago";
            } /* 1293 */ else if (region.equals("AR") == true) /* 1294 */ {
                timezone = "America/Chicago";
            } /* 1295 */ else if (region.equals("AZ") == true) /* 1296 */ {
                timezone = "America/Phoenix";
            } /* 1297 */ else if (region.equals("CA") == true) /* 1298 */ {
                timezone = "America/Los_Angeles";
            } /* 1299 */ else if (region.equals("CO") == true) /* 1300 */ {
                timezone = "America/Denver";
            } /* 1301 */ else if (region.equals("CT") == true) /* 1302 */ {
                timezone = "America/New_York";
            } /* 1303 */ else if (region.equals("DC") == true) /* 1304 */ {
                timezone = "America/New_York";
            } /* 1305 */ else if (region.equals("DE") == true) /* 1306 */ {
                timezone = "America/New_York";
            } /* 1307 */ else if (region.equals("FL") == true) /* 1308 */ {
                timezone = "America/New_York";
            } /* 1309 */ else if (region.equals("GA") == true) /* 1310 */ {
                timezone = "America/New_York";
            } /* 1311 */ else if (region.equals("HI") == true) /* 1312 */ {
                timezone = "Pacific/Honolulu";
            } /* 1313 */ else if (region.equals("IA") == true) /* 1314 */ {
                timezone = "America/Chicago";
            } /* 1315 */ else if (region.equals("ID") == true) /* 1316 */ {
                timezone = "America/Denver";
            } /* 1317 */ else if (region.equals("IL") == true) /* 1318 */ {
                timezone = "America/Chicago";
            } /* 1319 */ else if (region.equals("IN") == true) /* 1320 */ {
                timezone = "America/Indianapolis";
            } /* 1321 */ else if (region.equals("KS") == true) /* 1322 */ {
                timezone = "America/Chicago";
            } /* 1323 */ else if (region.equals("KY") == true) /* 1324 */ {
                timezone = "America/New_York";
            } /* 1325 */ else if (region.equals("LA") == true) /* 1326 */ {
                timezone = "America/Chicago";
            } /* 1327 */ else if (region.equals("MA") == true) /* 1328 */ {
                timezone = "America/New_York";
            } /* 1329 */ else if (region.equals("MD") == true) /* 1330 */ {
                timezone = "America/New_York";
            } /* 1331 */ else if (region.equals("ME") == true) /* 1332 */ {
                timezone = "America/New_York";
            } /* 1333 */ else if (region.equals("MI") == true) /* 1334 */ {
                timezone = "America/New_York";
            } /* 1335 */ else if (region.equals("MN") == true) /* 1336 */ {
                timezone = "America/Chicago";
            } /* 1337 */ else if (region.equals("MO") == true) /* 1338 */ {
                timezone = "America/Chicago";
            } /* 1339 */ else if (region.equals("MS") == true) /* 1340 */ {
                timezone = "America/Chicago";
            } /* 1341 */ else if (region.equals("MT") == true) /* 1342 */ {
                timezone = "America/Denver";
            } /* 1343 */ else if (region.equals("NC") == true) /* 1344 */ {
                timezone = "America/New_York";
            } /* 1345 */ else if (region.equals("ND") == true) /* 1346 */ {
                timezone = "America/Chicago";
            } /* 1347 */ else if (region.equals("NE") == true) /* 1348 */ {
                timezone = "America/Chicago";
            } /* 1349 */ else if (region.equals("NH") == true) /* 1350 */ {
                timezone = "America/New_York";
            } /* 1351 */ else if (region.equals("NJ") == true) /* 1352 */ {
                timezone = "America/New_York";
            } /* 1353 */ else if (region.equals("NM") == true) /* 1354 */ {
                timezone = "America/Denver";
            } /* 1355 */ else if (region.equals("NV") == true) /* 1356 */ {
                timezone = "America/Los_Angeles";
            } /* 1357 */ else if (region.equals("NY") == true) /* 1358 */ {
                timezone = "America/New_York";
            } /* 1359 */ else if (region.equals("OH") == true) /* 1360 */ {
                timezone = "America/New_York";
            } /* 1361 */ else if (region.equals("OK") == true) /* 1362 */ {
                timezone = "America/Chicago";
            } /* 1363 */ else if (region.equals("OR") == true) /* 1364 */ {
                timezone = "America/Los_Angeles";
            } /* 1365 */ else if (region.equals("PA") == true) /* 1366 */ {
                timezone = "America/New_York";
            } /* 1367 */ else if (region.equals("RI") == true) /* 1368 */ {
                timezone = "America/New_York";
            } /* 1369 */ else if (region.equals("SC") == true) /* 1370 */ {
                timezone = "America/New_York";
            } /* 1371 */ else if (region.equals("SD") == true) /* 1372 */ {
                timezone = "America/Chicago";
            } /* 1373 */ else if (region.equals("TN") == true) /* 1374 */ {
                timezone = "America/Chicago";
            } /* 1375 */ else if (region.equals("TX") == true) /* 1376 */ {
                timezone = "America/Chicago";
            } /* 1377 */ else if (region.equals("UT") == true) /* 1378 */ {
                timezone = "America/Denver";
            } /* 1379 */ else if (region.equals("VA") == true) /* 1380 */ {
                timezone = "America/New_York";
            } /* 1381 */ else if (region.equals("VT") == true) /* 1382 */ {
                timezone = "America/New_York";
            } /* 1383 */ else if (region.equals("WA") == true) /* 1384 */ {
                timezone = "America/Los_Angeles";
            } /* 1385 */ else if (region.equals("WI") == true) /* 1386 */ {
                timezone = "America/Chicago";
            } /* 1387 */ else if (region.equals("WV") == true) /* 1388 */ {
                timezone = "America/New_York";
            } /* 1389 */ else if (region.equals("WY") == true) /* 1390 */ {
                timezone = "America/Denver";
            }
        } /* 1392 */ else if (country.equals("UY") == true) /* 1393 */ {
            timezone = "America/Montevideo";
        } /* 1394 */ else if (country.equals("UZ") == true) {
            /* 1395 */ if (region.equals("01") == true) /* 1396 */ {
                timezone = "Asia/Tashkent";
            } /* 1397 */ else if (region.equals("02") == true) /* 1398 */ {
                timezone = "Asia/Samarkand";
            } /* 1399 */ else if (region.equals("03") == true) /* 1400 */ {
                timezone = "Asia/Tashkent";
            } /* 1401 */ else if (region.equals("05") == true) /* 1402 */ {
                timezone = "Asia/Samarkand";
            } /* 1403 */ else if (region.equals("06") == true) /* 1404 */ {
                timezone = "Asia/Tashkent";
            } /* 1405 */ else if (region.equals("07") == true) /* 1406 */ {
                timezone = "Asia/Samarkand";
            } /* 1407 */ else if (region.equals("08") == true) /* 1408 */ {
                timezone = "Asia/Samarkand";
            } /* 1409 */ else if (region.equals("10") == true) /* 1410 */ {
                timezone = "Asia/Samarkand";
            } /* 1411 */ else if (region.equals("12") == true) /* 1412 */ {
                timezone = "Asia/Samarkand";
            } /* 1413 */ else if (region.equals("13") == true) /* 1414 */ {
                timezone = "Asia/Tashkent";
            } /* 1415 */ else if (region.equals("14") == true) /* 1416 */ {
                timezone = "Asia/Tashkent";
            }
        } /* 1418 */ else if (country.equals("VA") == true) /* 1419 */ {
            timezone = "Europe/Vatican";
        } /* 1420 */ else if (country.equals("VC") == true) /* 1421 */ {
            timezone = "America/St_Vincent";
        } /* 1422 */ else if (country.equals("VE") == true) /* 1423 */ {
            timezone = "America/Caracas";
        } /* 1424 */ else if (country.equals("VG") == true) /* 1425 */ {
            timezone = "America/Tortola";
        } /* 1426 */ else if (country.equals("VI") == true) /* 1427 */ {
            timezone = "America/St_Thomas";
        } /* 1428 */ else if (country.equals("VN") == true) /* 1429 */ {
            timezone = "Asia/Ho_Chi_Minh";
        } /* 1430 */ else if (country.equals("VU") == true) /* 1431 */ {
            timezone = "Pacific/Efate";
        } /* 1432 */ else if (country.equals("WF") == true) /* 1433 */ {
            timezone = "Pacific/Wallis";
        } /* 1434 */ else if (country.equals("WS") == true) /* 1435 */ {
            timezone = "Pacific/Apia";
        } /* 1436 */ else if (country.equals("YE") == true) /* 1437 */ {
            timezone = "Asia/Aden";
        } /* 1438 */ else if (country.equals("YT") == true) /* 1439 */ {
            timezone = "Indian/Mayotte";
        } /* 1440 */ else if (country.equals("ZA") == true) /* 1441 */ {
            timezone = "Africa/Johannesburg";
        } /* 1442 */ else if (country.equals("ZM") == true) /* 1443 */ {
            timezone = "Africa/Lusaka";
        } /* 1444 */ else if (country.equals("ZW") == true) /* 1445 */ {
            timezone = "Africa/Harare";
        } /* 1446 */ else if (country.equals("SX") == true) /* 1447 */ {
            timezone = "America/Curacao";
        } /* 1448 */ else if (country.equals("BQ") == true) /* 1449 */ {
            timezone = "America/Curacao";
        } /* 1450 */ else if (country.equals("CW") == true) /* 1451 */ {
            timezone = "America/Curacao";
        } /* 1452 */ else if (country.equals("BL") == true) /* 1453 */ {
            timezone = "America/St_Barthelemy";
        } /* 1454 */ else if (country.equals("PN") == true) {
            /* 1455 */ timezone = "Pacific/Pitcairn";
        }
        /* 1457 */ return timezone;
    }
}

/* Location:           /home/alberto/desarrollo/troyano/Adwind RAT v3.0_boredliner+plugins/Adwind RAT v3.0_boredliner/Favicon 1/0Adwind RAT v3.0_boredliner.jar
 * Qualified Name:     com.maxmind.geoip.timeZone
 * JD-Core Version:    0.6.2
 */
