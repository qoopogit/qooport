package com.maxmind.geoip;

public class regionName {

    public static String regionNameByCode(String country_code, String region_code) {
        String name = null;
        int region_code2 = -1;
        if (region_code == null) {
            return null;
        }
        if (region_code.equals("")) {
            return null;
        }

        if ((region_code.charAt(0) >= '0') && (region_code.charAt(0) < ':') && (region_code.charAt(1) >= '0') && (region_code.charAt(1) < ':')) {
            region_code2 = (region_code.charAt(0) - '0') * 10 + region_code.charAt(1) - 48;
        } else if (((region_code.charAt(0) >= 'A') && (region_code.charAt(0) < '[')) || ((region_code.charAt(0) >= '0') && (region_code.charAt(0) < ':') && (((region_code.charAt(1) >= 'A') && (region_code.charAt(1) < '[')) || ((region_code.charAt(1) >= '0') && (region_code.charAt(1) < ':'))))) {
            region_code2 = (region_code.charAt(0) - '0') * 43 + region_code.charAt(1) - 48 + 100;
        }

        if (region_code2 == -1) {
            return null;
        }
        if (country_code.equals("CA") == true) {
            switch (region_code2) {
                case 849:
                    name = "Alberta";
                    break;
                case 893:
                    name = "British Columbia";
                    break;
                case 1365:
                    name = "Manitoba";
                    break;
                case 1408:
                    /*    38 */ name = "New Brunswick";
                    /*    39 */ break;
                case 1418:
                    /*    41 */ name = "Newfoundland";
                    /*    42 */ break;
                case 1425:
                    /*    44 */ name = "Nova Scotia";
                    /*    45 */ break;
                case 1427:
                    /*    47 */ name = "Nunavut";
                    /*    48 */ break;
                case 1463:
                    /*    50 */ name = "Ontario";
                    /*    51 */ break;
                case 1497:
                    /*    53 */ name = "Prince Edward Island";
                    /*    54 */ break;
                case 1538:
                    /*    56 */ name = "Quebec";
                    /*    57 */ break;
                case 1632:
                    /*    59 */ name = "Saskatchewan";
                    /*    60 */ break;
                case 1426:
                    /*    62 */ name = "Northwest Territories";
                    /*    63 */ break;
                case 1899:
                    /*    65 */ name = "Yukon Territory";
            }
        }

        /*    69 */ if (country_code.equals("US") == true) {
            /*    70 */ switch (region_code2) {
                case 848:
                    /*    72 */ name = "Armed Forces Americas";
                    /*    73 */ break;
                case 852:
                    /*    75 */ name = "Armed Forces Europe";
                    /*    76 */ break;
                case 858:
                    /*    78 */ name = "Alaska";
                    /*    79 */ break;
                case 859:
                    /*    81 */ name = "Alabama";
                    /*    82 */ break;
                case 863:
                    /*    84 */ name = "Armed Forces Pacific";
                    /*    85 */ break;
                case 865:
                    /*    87 */ name = "Arkansas";
                    /*    88 */ break;
                case 866:
                    /*    90 */ name = "American Samoa";
                    /*    91 */ break;
                case 873:
                    /*    93 */ name = "Arizona";
                    /*    94 */ break;
                case 934:
                    /*    96 */ name = "California";
                    /*    97 */ break;
                case 948:
                    /*    99 */ name = "Colorado";
                    /*   100 */ break;
                case 953:
                    /*   102 */ name = "Connecticut";
                    /*   103 */ break;
                case 979:
                    /*   105 */ name = "District of Columbia";
                    /*   106 */ break;
                case 981:
                    /*   108 */ name = "Delaware";
                    /*   109 */ break;
                case 1074:
                    /*   111 */ name = "Florida";
                    /*   112 */ break;
                case 1075:
                    /*   114 */ name = "Federated States of Micronesia";
                    /*   115 */ break;
                case 1106:
                    /*   117 */ name = "Georgia";
                    /*   118 */ break;
                case 1126:
                    /*   120 */ name = "Guam";
                    /*   121 */ break;
                case 1157:
                    /*   123 */ name = "Hawaii";
                    /*   124 */ break;
                case 1192:
                    /*   126 */ name = "Iowa";
                    /*   127 */ break;
                case 1195:
                    /*   129 */ name = "Idaho";
                    /*   130 */ break;
                case 1203:
                    /*   132 */ name = "Illinois";
                    /*   133 */ break;
                case 1205:
                    /*   135 */ name = "Indiana";
                    /*   136 */ break;
                case 1296:
                    /*   138 */ name = "Kansas";
                    /*   139 */ break;
                case 1302:
                    /*   141 */ name = "Kentucky";
                    /*   142 */ break;
                case 1321:
                    /*   144 */ name = "Louisiana";
                    /*   145 */ break;
                case 1364:
                    /*   147 */ name = "Massachusetts";
                    /*   148 */ break;
                case 1367:
                    /*   150 */ name = "Maryland";
                    /*   151 */ break;
                case 1368:
                    /*   153 */ name = "Maine";
                    /*   154 */ break;
                case 1371:
                    /*   156 */ name = "Marshall Islands";
                    /*   157 */ break;
                case 1372:
                    /*   159 */ name = "Michigan";
                    /*   160 */ break;
                case 1377:
                    /*   162 */ name = "Minnesota";
                    /*   163 */ break;
                case 1378:
                    /*   165 */ name = "Missouri";
                    /*   166 */ break;
                case 1379:
                    /*   168 */ name = "Northern Mariana Islands";
                    /*   169 */ break;
                case 1382:
                    /*   171 */ name = "Mississippi";
                    /*   172 */ break;
                case 1383:
                    /*   174 */ name = "Montana";
                    /*   175 */ break;
                case 1409:
                    /*   177 */ name = "North Carolina";
                    /*   178 */ break;
                case 1410:
                    /*   180 */ name = "North Dakota";
                    /*   181 */ break;
                case 1411:
                    /*   183 */ name = "Nebraska";
                    /*   184 */ break;
                case 1414:
                    /*   186 */ name = "New Hampshire";
                    /*   187 */ break;
                case 1416:
                    /*   189 */ name = "New Jersey";
                    /*   190 */ break;
                case 1419:
                    /*   192 */ name = "New Mexico";
                    /*   193 */ break;
                case 1428:
                    /*   195 */ name = "Nevada";
                    /*   196 */ break;
                case 1431:
                    /*   198 */ name = "New York";
                    /*   199 */ break;
                case 1457:
                    /*   201 */ name = "Ohio";
                    /*   202 */ break;
                case 1460:
                    /*   204 */ name = "Oklahoma";
                    /*   205 */ break;
                case 1467:
                    /*   207 */ name = "Oregon";
                    /*   208 */ break;
                case 1493:
                    /*   210 */ name = "Pennsylvania";
                    /*   211 */ break;
                case 1510:
                    /*   213 */ name = "Puerto Rico";
                    /*   214 */ break;
                case 1515:
                    /*   216 */ name = "Palau";
                    /*   217 */ break;
                case 1587:
                    /*   219 */ name = "Rhode Island";
                    /*   220 */ break;
                case 1624:
                    /*   222 */ name = "South Carolina";
                    /*   223 */ break;
                case 1625:
                    /*   225 */ name = "South Dakota";
                    /*   226 */ break;
                case 1678:
                    /*   228 */ name = "Tennessee";
                    /*   229 */ break;
                case 1688:
                    /*   231 */ name = "Texas";
                    /*   232 */ break;
                case 1727:
                    /*   234 */ name = "Utah";
                    /*   235 */ break;
                case 1751:
                    /*   237 */ name = "Virginia";
                    /*   238 */ break;
                case 1759:
                    /*   240 */ name = "Virgin Islands";
                    /*   241 */ break;
                case 1770:
                    /*   243 */ name = "Vermont";
                    /*   244 */ break;
                case 1794:
                    /*   246 */ name = "Washington";
                    /*   247 */ break;
                case 1815:
                    /*   249 */ name = "West Virginia";
                    /*   250 */ break;
                case 1802:
                    /*   252 */ name = "Wisconsin";
                    /*   253 */ break;
                case 1818:
                    /*   255 */ name = "Wyoming";
            }
        }

        /*   259 */ if (country_code.equals("AD") == true) {
            /*   260 */ switch (region_code2) {
                case 2:
                    /*   262 */ name = "Canillo";
                    /*   263 */ break;
                case 3:
                    /*   265 */ name = "Encamp";
                    /*   266 */ break;
                case 4:
                    /*   268 */ name = "La Massana";
                    /*   269 */ break;
                case 5:
                    /*   271 */ name = "Ordino";
                    /*   272 */ break;
                case 6:
                    /*   274 */ name = "Sant Julia de Loria";
                    /*   275 */ break;
                case 7:
                    /*   277 */ name = "Andorra la Vella";
                    /*   278 */ break;
                case 8:
                    /*   280 */ name = "Escaldes-Engordany";
            }
        }

        /*   284 */ if (country_code.equals("AE") == true) {
            /*   285 */ switch (region_code2) {
                case 1:
                    /*   287 */ name = "Abu Dhabi";
                    /*   288 */ break;
                case 2:
                    /*   290 */ name = "Ajman";
                    /*   291 */ break;
                case 3:
                    /*   293 */ name = "Dubai";
                    /*   294 */ break;
                case 4:
                    /*   296 */ name = "Fujairah";
                    /*   297 */ break;
                case 5:
                    /*   299 */ name = "Ras Al Khaimah";
                    /*   300 */ break;
                case 6:
                    /*   302 */ name = "Sharjah";
                    /*   303 */ break;
                case 7:
                    /*   305 */ name = "Umm Al Quwain";
            }
        }

        /*   309 */ if (country_code.equals("AF") == true) /*   310 */ {
            switch (region_code2) {
                case 1:
                    /*   312 */ name = "Badakhshan";
                    /*   313 */ break;
                case 2:
                    /*   315 */ name = "Badghis";
                    /*   316 */ break;
                case 3:
                    /*   318 */ name = "Baghlan";
                    /*   319 */ break;
                case 5:
                    /*   321 */ name = "Bamian";
                    /*   322 */ break;
                case 6:
                    /*   324 */ name = "Farah";
                    /*   325 */ break;
                case 7:
                    /*   327 */ name = "Faryab";
                    /*   328 */ break;
                case 8:
                    /*   330 */ name = "Ghazni";
                    /*   331 */ break;
                case 9:
                    /*   333 */ name = "Ghowr";
                    /*   334 */ break;
                case 10:
                    /*   336 */ name = "Helmand";
                    /*   337 */ break;
                case 11:
                    /*   339 */ name = "Herat";
                    /*   340 */ break;
                case 13:
                    /*   342 */ name = "Kabol";
                    /*   343 */ break;
                case 14:
                    /*   345 */ name = "Kapisa";
                    /*   346 */ break;
                case 17:
                    /*   348 */ name = "Lowgar";
                    /*   349 */ break;
                case 18:
                    /*   351 */ name = "Nangarhar";
                    /*   352 */ break;
                case 19:
                    /*   354 */ name = "Nimruz";
                    /*   355 */ break;
                case 23:
                    /*   357 */ name = "Kandahar";
                    /*   358 */ break;
                case 24:
                    /*   360 */ name = "Kondoz";
                    /*   361 */ break;
                case 26:
                    /*   363 */ name = "Takhar";
                    /*   364 */ break;
                case 27:
                    /*   366 */ name = "Vardak";
                    /*   367 */ break;
                case 28:
                    /*   369 */ name = "Zabol";
                    /*   370 */ break;
                case 29:
                    /*   372 */ name = "Paktika";
                    /*   373 */ break;
                case 30:
                    /*   375 */ name = "Balkh";
                    /*   376 */ break;
                case 31:
                    /*   378 */ name = "Jowzjan";
                    /*   379 */ break;
                case 32:
                    /*   381 */ name = "Samangan";
                    /*   382 */ break;
                case 33:
                    /*   384 */ name = "Sar-e Pol";
                    /*   385 */ break;
                case 34:
                    /*   387 */ name = "Konar";
                    /*   388 */ break;
                case 35:
                    /*   390 */ name = "Laghman";
                    /*   391 */ break;
                case 36:
                    /*   393 */ name = "Paktia";
                    /*   394 */ break;
                case 37:
                    /*   396 */ name = "Khowst";
                    /*   397 */ break;
                case 38:
                    /*   399 */ name = "Nurestan";
                    /*   400 */ break;
                case 39:
                    /*   402 */ name = "Oruzgan";
                    /*   403 */ break;
                case 40:
                    /*   405 */ name = "Parvan";
                    /*   406 */ break;
                case 41:
                    /*   408 */ name = "Daykondi";
                    /*   409 */ break;
                case 42:
                    /*   411 */ name = "Panjshir";
                case 4:
                case 12:
                case 15:
                case 16:
                case 20:
                case 21:
                case 22:
                /*   415 */ case 25:
            }
        }
        if (country_code.equals("AG") == true) {
            /*   416 */ switch (region_code2) {
                case 1:
                    /*   418 */ name = "Barbuda";
                    /*   419 */ break;
                case 3:
                    /*   421 */ name = "Saint George";
                    /*   422 */ break;
                case 4:
                    /*   424 */ name = "Saint John";
                    /*   425 */ break;
                case 5:
                    /*   427 */ name = "Saint Mary";
                    /*   428 */ break;
                case 6:
                    /*   430 */ name = "Saint Paul";
                    /*   431 */ break;
                case 7:
                    /*   433 */ name = "Saint Peter";
                    /*   434 */ break;
                case 8:
                    /*   436 */ name = "Saint Philip";
                    /*   437 */ break;
                case 9:
                    /*   439 */ name = "Redonda";
                case 2:
            }
        }
        /*   443 */ if (country_code.equals("AL") == true) {
            /*   444 */ switch (region_code2) {
                case 40:
                    /*   446 */ name = "Berat";
                    /*   447 */ break;
                case 41:
                    /*   449 */ name = "Diber";
                    /*   450 */ break;
                case 42:
                    /*   452 */ name = "Durres";
                    /*   453 */ break;
                case 43:
                    /*   455 */ name = "Elbasan";
                    /*   456 */ break;
                case 44:
                    /*   458 */ name = "Fier";
                    /*   459 */ break;
                case 45:
                    /*   461 */ name = "Gjirokaster";
                    /*   462 */ break;
                case 46:
                    /*   464 */ name = "Korce";
                    /*   465 */ break;
                case 47:
                    /*   467 */ name = "Kukes";
                    /*   468 */ break;
                case 48:
                    /*   470 */ name = "Lezhe";
                    /*   471 */ break;
                case 49:
                    /*   473 */ name = "Shkoder";
                    /*   474 */ break;
                case 50:
                    /*   476 */ name = "Tirane";
                    /*   477 */ break;
                case 51:
                    /*   479 */ name = "Vlore";
            }
        }

        /*   483 */ if (country_code.equals("AM") == true) {
            /*   484 */ switch (region_code2) {
                case 1:
                    /*   486 */ name = "Aragatsotn";
                    /*   487 */ break;
                case 2:
                    /*   489 */ name = "Ararat";
                    /*   490 */ break;
                case 3:
                    /*   492 */ name = "Armavir";
                    /*   493 */ break;
                case 4:
                    /*   495 */ name = "Geghark'unik'";
                    /*   496 */ break;
                case 5:
                    /*   498 */ name = "Kotayk'";
                    /*   499 */ break;
                case 6:
                    /*   501 */ name = "Lorri";
                    /*   502 */ break;
                case 7:
                    /*   504 */ name = "Shirak";
                    /*   505 */ break;
                case 8:
                    /*   507 */ name = "Syunik'";
                    /*   508 */ break;
                case 9:
                    /*   510 */ name = "Tavush";
                    /*   511 */ break;
                case 10:
                    /*   513 */ name = "Vayots' Dzor";
                    /*   514 */ break;
                case 11:
                    /*   516 */ name = "Yerevan";
            }
        }

        /*   520 */ if (country_code.equals("AO") == true) /*   521 */ {
            switch (region_code2) {
                case 1:
                    /*   523 */ name = "Benguela";
                    /*   524 */ break;
                case 2:
                    /*   526 */ name = "Bie";
                    /*   527 */ break;
                case 3:
                    /*   529 */ name = "Cabinda";
                    /*   530 */ break;
                case 4:
                    /*   532 */ name = "Cuando Cubango";
                    /*   533 */ break;
                case 5:
                    /*   535 */ name = "Cuanza Norte";
                    /*   536 */ break;
                case 6:
                    /*   538 */ name = "Cuanza Sul";
                    /*   539 */ break;
                case 7:
                    /*   541 */ name = "Cunene";
                    /*   542 */ break;
                case 8:
                    /*   544 */ name = "Huambo";
                    /*   545 */ break;
                case 9:
                    /*   547 */ name = "Huila";
                    /*   548 */ break;
                case 12:
                    /*   550 */ name = "Malanje";
                    /*   551 */ break;
                case 13:
                    /*   553 */ name = "Namibe";
                    /*   554 */ break;
                case 14:
                    /*   556 */ name = "Moxico";
                    /*   557 */ break;
                case 15:
                    /*   559 */ name = "Uige";
                    /*   560 */ break;
                case 16:
                    /*   562 */ name = "Zaire";
                    /*   563 */ break;
                case 17:
                    /*   565 */ name = "Lunda Norte";
                    /*   566 */ break;
                case 18:
                    /*   568 */ name = "Lunda Sul";
                    /*   569 */ break;
                case 19:
                    /*   571 */ name = "Bengo";
                    /*   572 */ break;
                case 20:
                    /*   574 */ name = "Luanda";
                case 10:
                case 11:
            }
        }
        /*   578 */ if (country_code.equals("AR") == true) {
            /*   579 */ switch (region_code2) {
                case 1:
                    /*   581 */ name = "Buenos Aires";
                    /*   582 */ break;
                case 2:
                    /*   584 */ name = "Catamarca";
                    /*   585 */ break;
                case 3:
                    /*   587 */ name = "Chaco";
                    /*   588 */ break;
                case 4:
                    /*   590 */ name = "Chubut";
                    /*   591 */ break;
                case 5:
                    /*   593 */ name = "Cordoba";
                    /*   594 */ break;
                case 6:
                    /*   596 */ name = "Corrientes";
                    /*   597 */ break;
                case 7:
                    /*   599 */ name = "Distrito Federal";
                    /*   600 */ break;
                case 8:
                    /*   602 */ name = "Entre Rios";
                    /*   603 */ break;
                case 9:
                    /*   605 */ name = "Formosa";
                    /*   606 */ break;
                case 10:
                    /*   608 */ name = "Jujuy";
                    /*   609 */ break;
                case 11:
                    /*   611 */ name = "La Pampa";
                    /*   612 */ break;
                case 12:
                    /*   614 */ name = "La Rioja";
                    /*   615 */ break;
                case 13:
                    /*   617 */ name = "Mendoza";
                    /*   618 */ break;
                case 14:
                    /*   620 */ name = "Misiones";
                    /*   621 */ break;
                case 15:
                    /*   623 */ name = "Neuquen";
                    /*   624 */ break;
                case 16:
                    /*   626 */ name = "Rio Negro";
                    /*   627 */ break;
                case 17:
                    /*   629 */ name = "Salta";
                    /*   630 */ break;
                case 18:
                    /*   632 */ name = "San Juan";
                    /*   633 */ break;
                case 19:
                    /*   635 */ name = "San Luis";
                    /*   636 */ break;
                case 20:
                    /*   638 */ name = "Santa Cruz";
                    /*   639 */ break;
                case 21:
                    /*   641 */ name = "Santa Fe";
                    /*   642 */ break;
                case 22:
                    /*   644 */ name = "Santiago del Estero";
                    /*   645 */ break;
                case 23:
                    /*   647 */ name = "Tierra del Fuego";
                    /*   648 */ break;
                case 24:
                    /*   650 */ name = "Tucuman";
            }
        }

        /*   654 */ if (country_code.equals("AT") == true) {
            /*   655 */ switch (region_code2) {
                case 1:
                    /*   657 */ name = "Burgenland";
                    /*   658 */ break;
                case 2:
                    /*   660 */ name = "Karnten";
                    /*   661 */ break;
                case 3:
                    /*   663 */ name = "Niederosterreich";
                    /*   664 */ break;
                case 4:
                    /*   666 */ name = "Oberosterreich";
                    /*   667 */ break;
                case 5:
                    /*   669 */ name = "Salzburg";
                    /*   670 */ break;
                case 6:
                    /*   672 */ name = "Steiermark";
                    /*   673 */ break;
                case 7:
                    /*   675 */ name = "Tirol";
                    /*   676 */ break;
                case 8:
                    /*   678 */ name = "Vorarlberg";
                    /*   679 */ break;
                case 9:
                    /*   681 */ name = "Wien";
            }
        }

        /*   685 */ if (country_code.equals("AU") == true) {
            /*   686 */ switch (region_code2) {
                case 1:
                    /*   688 */ name = "Australian Capital Territory";
                    /*   689 */ break;
                case 2:
                    /*   691 */ name = "New South Wales";
                    /*   692 */ break;
                case 3:
                    /*   694 */ name = "Northern Territory";
                    /*   695 */ break;
                case 4:
                    /*   697 */ name = "Queensland";
                    /*   698 */ break;
                case 5:
                    /*   700 */ name = "South Australia";
                    /*   701 */ break;
                case 6:
                    /*   703 */ name = "Tasmania";
                    /*   704 */ break;
                case 7:
                    /*   706 */ name = "Victoria";
                    /*   707 */ break;
                case 8:
                    /*   709 */ name = "Western Australia";
            }
        }

        /*   713 */ if (country_code.equals("AZ") == true) {
            /*   714 */ switch (region_code2) {
                case 1:
                    /*   716 */ name = "Abseron";
                    /*   717 */ break;
                case 2:
                    /*   719 */ name = "Agcabadi";
                    /*   720 */ break;
                case 3:
                    /*   722 */ name = "Agdam";
                    /*   723 */ break;
                case 4:
                    /*   725 */ name = "Agdas";
                    /*   726 */ break;
                case 5:
                    /*   728 */ name = "Agstafa";
                    /*   729 */ break;
                case 6:
                    /*   731 */ name = "Agsu";
                    /*   732 */ break;
                case 7:
                    /*   734 */ name = "Ali Bayramli";
                    /*   735 */ break;
                case 8:
                    /*   737 */ name = "Astara";
                    /*   738 */ break;
                case 9:
                    /*   740 */ name = "Baki";
                    /*   741 */ break;
                case 10:
                    /*   743 */ name = "Balakan";
                    /*   744 */ break;
                case 11:
                    /*   746 */ name = "Barda";
                    /*   747 */ break;
                case 12:
                    /*   749 */ name = "Beylaqan";
                    /*   750 */ break;
                case 13:
                    /*   752 */ name = "Bilasuvar";
                    /*   753 */ break;
                case 14:
                    /*   755 */ name = "Cabrayil";
                    /*   756 */ break;
                case 15:
                    /*   758 */ name = "Calilabad";
                    /*   759 */ break;
                case 16:
                    /*   761 */ name = "Daskasan";
                    /*   762 */ break;
                case 17:
                    /*   764 */ name = "Davaci";
                    /*   765 */ break;
                case 18:
                    /*   767 */ name = "Fuzuli";
                    /*   768 */ break;
                case 19:
                    /*   770 */ name = "Gadabay";
                    /*   771 */ break;
                case 20:
                    /*   773 */ name = "Ganca";
                    /*   774 */ break;
                case 21:
                    /*   776 */ name = "Goranboy";
                    /*   777 */ break;
                case 22:
                    /*   779 */ name = "Goycay";
                    /*   780 */ break;
                case 23:
                    /*   782 */ name = "Haciqabul";
                    /*   783 */ break;
                case 24:
                    /*   785 */ name = "Imisli";
                    /*   786 */ break;
                case 25:
                    /*   788 */ name = "Ismayilli";
                    /*   789 */ break;
                case 26:
                    /*   791 */ name = "Kalbacar";
                    /*   792 */ break;
                case 27:
                    /*   794 */ name = "Kurdamir";
                    /*   795 */ break;
                case 28:
                    /*   797 */ name = "Lacin";
                    /*   798 */ break;
                case 29:
                    /*   800 */ name = "Lankaran";
                    /*   801 */ break;
                case 30:
                    /*   803 */ name = "Lankaran";
                    /*   804 */ break;
                case 31:
                    /*   806 */ name = "Lerik";
                    /*   807 */ break;
                case 32:
                    /*   809 */ name = "Masalli";
                    /*   810 */ break;
                case 33:
                    /*   812 */ name = "Mingacevir";
                    /*   813 */ break;
                case 34:
                    /*   815 */ name = "Naftalan";
                    /*   816 */ break;
                case 35:
                    /*   818 */ name = "Naxcivan";
                    /*   819 */ break;
                case 36:
                    /*   821 */ name = "Neftcala";
                    /*   822 */ break;
                case 37:
                    /*   824 */ name = "Oguz";
                    /*   825 */ break;
                case 38:
                    /*   827 */ name = "Qabala";
                    /*   828 */ break;
                case 39:
                    /*   830 */ name = "Qax";
                    /*   831 */ break;
                case 40:
                    /*   833 */ name = "Qazax";
                    /*   834 */ break;
                case 41:
                    /*   836 */ name = "Qobustan";
                    /*   837 */ break;
                case 42:
                    /*   839 */ name = "Quba";
                    /*   840 */ break;
                case 43:
                    /*   842 */ name = "Qubadli";
                    /*   843 */ break;
                case 44:
                    /*   845 */ name = "Qusar";
                    /*   846 */ break;
                case 45:
                    /*   848 */ name = "Saatli";
                    /*   849 */ break;
                case 46:
                    /*   851 */ name = "Sabirabad";
                    /*   852 */ break;
                case 47:
                    /*   854 */ name = "Saki";
                    /*   855 */ break;
                case 48:
                    /*   857 */ name = "Saki";
                    /*   858 */ break;
                case 49:
                    /*   860 */ name = "Salyan";
                    /*   861 */ break;
                case 50:
                    /*   863 */ name = "Samaxi";
                    /*   864 */ break;
                case 51:
                    /*   866 */ name = "Samkir";
                    /*   867 */ break;
                case 52:
                    /*   869 */ name = "Samux";
                    /*   870 */ break;
                case 53:
                    /*   872 */ name = "Siyazan";
                    /*   873 */ break;
                case 54:
                    /*   875 */ name = "Sumqayit";
                    /*   876 */ break;
                case 55:
                    /*   878 */ name = "Susa";
                    /*   879 */ break;
                case 56:
                    /*   881 */ name = "Susa";
                    /*   882 */ break;
                case 57:
                    /*   884 */ name = "Tartar";
                    /*   885 */ break;
                case 58:
                    /*   887 */ name = "Tovuz";
                    /*   888 */ break;
                case 59:
                    /*   890 */ name = "Ucar";
                    /*   891 */ break;
                case 60:
                    /*   893 */ name = "Xacmaz";
                    /*   894 */ break;
                case 61:
                    /*   896 */ name = "Xankandi";
                    /*   897 */ break;
                case 62:
                    /*   899 */ name = "Xanlar";
                    /*   900 */ break;
                case 63:
                    /*   902 */ name = "Xizi";
                    /*   903 */ break;
                case 64:
                    /*   905 */ name = "Xocali";
                    /*   906 */ break;
                case 65:
                    /*   908 */ name = "Xocavand";
                    /*   909 */ break;
                case 66:
                    /*   911 */ name = "Yardimli";
                    /*   912 */ break;
                case 67:
                    /*   914 */ name = "Yevlax";
                    /*   915 */ break;
                case 68:
                    /*   917 */ name = "Yevlax";
                    /*   918 */ break;
                case 69:
                    /*   920 */ name = "Zangilan";
                    /*   921 */ break;
                case 70:
                    /*   923 */ name = "Zaqatala";
                    /*   924 */ break;
                case 71:
                    /*   926 */ name = "Zardab";
            }
        }

        /*   930 */ if (country_code.equals("BA") == true) {
            /*   931 */ switch (region_code2) {
                case 1:
                    /*   933 */ name = "Federation of Bosnia and Herzegovina";
                    /*   934 */ break;
                case 2:
                    /*   936 */ name = "Republika Srpska";
            }
        }

        /*   940 */ if (country_code.equals("BB") == true) {
            /*   941 */ switch (region_code2) {
                case 1:
                    /*   943 */ name = "Christ Church";
                    /*   944 */ break;
                case 2:
                    /*   946 */ name = "Saint Andrew";
                    /*   947 */ break;
                case 3:
                    /*   949 */ name = "Saint George";
                    /*   950 */ break;
                case 4:
                    /*   952 */ name = "Saint James";
                    /*   953 */ break;
                case 5:
                    /*   955 */ name = "Saint John";
                    /*   956 */ break;
                case 6:
                    /*   958 */ name = "Saint Joseph";
                    /*   959 */ break;
                case 7:
                    /*   961 */ name = "Saint Lucy";
                    /*   962 */ break;
                case 8:
                    /*   964 */ name = "Saint Michael";
                    /*   965 */ break;
                case 9:
                    /*   967 */ name = "Saint Peter";
                    /*   968 */ break;
                case 10:
                    /*   970 */ name = "Saint Philip";
                    /*   971 */ break;
                case 11:
                    /*   973 */ name = "Saint Thomas";
            }
        }

        /*   977 */ if (country_code.equals("BD") == true) {
            /*   978 */ switch (region_code2) {
                case 81:
                    /*   980 */ name = "Dhaka";
                    /*   981 */ break;
                case 82:
                    /*   983 */ name = "Khulna";
                    /*   984 */ break;
                case 83:
                    /*   986 */ name = "Rajshahi";
                    /*   987 */ break;
                case 84:
                    /*   989 */ name = "Chittagong";
                    /*   990 */ break;
                case 85:
                    /*   992 */ name = "Barisal";
                    /*   993 */ break;
                case 86:
                    /*   995 */ name = "Sylhet";
            }
        }

        /*   999 */ if (country_code.equals("BE") == true) {
            /*  1000 */ switch (region_code2) {
                case 1:
                    /*  1002 */ name = "Antwerpen";
                    /*  1003 */ break;
                case 3:
                    /*  1005 */ name = "Hainaut";
                    /*  1006 */ break;
                case 4:
                    /*  1008 */ name = "Liege";
                    /*  1009 */ break;
                case 5:
                    /*  1011 */ name = "Limburg";
                    /*  1012 */ break;
                case 6:
                    /*  1014 */ name = "Luxembourg";
                    /*  1015 */ break;
                case 7:
                    /*  1017 */ name = "Namur";
                    /*  1018 */ break;
                case 8:
                    /*  1020 */ name = "Oost-Vlaanderen";
                    /*  1021 */ break;
                case 9:
                    /*  1023 */ name = "West-Vlaanderen";
                    /*  1024 */ break;
                case 10:
                    /*  1026 */ name = "Brabant Wallon";
                    /*  1027 */ break;
                case 11:
                    /*  1029 */ name = "Brussels Hoofdstedelijk Gewest";
                    /*  1030 */ break;
                case 12:
                    /*  1032 */ name = "Vlaams-Brabant";
                    /*  1033 */ break;
                case 13:
                    /*  1035 */ name = "Flanders";
                    /*  1036 */ break;
                case 14:
                    /*  1038 */ name = "Wallonia";
                case 2:
            }
        }
        /*  1042 */ if (country_code.equals("BF") == true) /*  1043 */ {
            switch (region_code2) {
                case 15:
                    /*  1045 */ name = "Bam";
                    /*  1046 */ break;
                case 19:
                    /*  1048 */ name = "Boulkiemde";
                    /*  1049 */ break;
                case 20:
                    /*  1051 */ name = "Ganzourgou";
                    /*  1052 */ break;
                case 21:
                    /*  1054 */ name = "Gnagna";
                    /*  1055 */ break;
                case 28:
                    /*  1057 */ name = "Kouritenga";
                    /*  1058 */ break;
                case 33:
                    /*  1060 */ name = "Oudalan";
                    /*  1061 */ break;
                case 34:
                    /*  1063 */ name = "Passore";
                    /*  1064 */ break;
                case 36:
                    /*  1066 */ name = "Sanguie";
                    /*  1067 */ break;
                case 40:
                    /*  1069 */ name = "Soum";
                    /*  1070 */ break;
                case 42:
                    /*  1072 */ name = "Tapoa";
                    /*  1073 */ break;
                case 44:
                    /*  1075 */ name = "Zoundweogo";
                    /*  1076 */ break;
                case 45:
                    /*  1078 */ name = "Bale";
                    /*  1079 */ break;
                case 46:
                    /*  1081 */ name = "Banwa";
                    /*  1082 */ break;
                case 47:
                    /*  1084 */ name = "Bazega";
                    /*  1085 */ break;
                case 48:
                    /*  1087 */ name = "Bougouriba";
                    /*  1088 */ break;
                case 49:
                    /*  1090 */ name = "Boulgou";
                    /*  1091 */ break;
                case 50:
                    /*  1093 */ name = "Gourma";
                    /*  1094 */ break;
                case 51:
                    /*  1096 */ name = "Houet";
                    /*  1097 */ break;
                case 52:
                    /*  1099 */ name = "Ioba";
                    /*  1100 */ break;
                case 53:
                    /*  1102 */ name = "Kadiogo";
                    /*  1103 */ break;
                case 54:
                    /*  1105 */ name = "Kenedougou";
                    /*  1106 */ break;
                case 55:
                    /*  1108 */ name = "Komoe";
                    /*  1109 */ break;
                case 56:
                    /*  1111 */ name = "Komondjari";
                    /*  1112 */ break;
                case 57:
                    /*  1114 */ name = "Kompienga";
                    /*  1115 */ break;
                case 58:
                    /*  1117 */ name = "Kossi";
                    /*  1118 */ break;
                case 59:
                    /*  1120 */ name = "Koulpelogo";
                    /*  1121 */ break;
                case 60:
                    /*  1123 */ name = "Kourweogo";
                    /*  1124 */ break;
                case 61:
                    /*  1126 */ name = "Leraba";
                    /*  1127 */ break;
                case 62:
                    /*  1129 */ name = "Loroum";
                    /*  1130 */ break;
                case 63:
                    /*  1132 */ name = "Mouhoun";
                    /*  1133 */ break;
                case 64:
                    /*  1135 */ name = "Namentenga";
                    /*  1136 */ break;
                case 65:
                    /*  1138 */ name = "Naouri";
                    /*  1139 */ break;
                case 66:
                    /*  1141 */ name = "Nayala";
                    /*  1142 */ break;
                case 67:
                    /*  1144 */ name = "Noumbiel";
                    /*  1145 */ break;
                case 68:
                    /*  1147 */ name = "Oubritenga";
                    /*  1148 */ break;
                case 69:
                    /*  1150 */ name = "Poni";
                    /*  1151 */ break;
                case 70:
                    /*  1153 */ name = "Sanmatenga";
                    /*  1154 */ break;
                case 71:
                    /*  1156 */ name = "Seno";
                    /*  1157 */ break;
                case 72:
                    /*  1159 */ name = "Sissili";
                    /*  1160 */ break;
                case 73:
                    /*  1162 */ name = "Sourou";
                    /*  1163 */ break;
                case 74:
                    /*  1165 */ name = "Tuy";
                    /*  1166 */ break;
                case 75:
                    /*  1168 */ name = "Yagha";
                    /*  1169 */ break;
                case 76:
                    /*  1171 */ name = "Yatenga";
                    /*  1172 */ break;
                case 77:
                    /*  1174 */ name = "Ziro";
                    /*  1175 */ break;
                case 78:
                    /*  1177 */ name = "Zondoma";
                case 16:
                case 17:
                case 18:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 29:
                case 30:
                case 31:
                case 32:
                case 35:
                case 37:
                case 38:
                case 39:
                case 41:
                /*  1181 */ case 43:
            }
        }
        if (country_code.equals("BG") == true) /*  1182 */ {
            switch (region_code2) {
                case 33:
                    /*  1184 */ name = "Mikhaylovgrad";
                    /*  1185 */ break;
                case 38:
                    /*  1187 */ name = "Blagoevgrad";
                    /*  1188 */ break;
                case 39:
                    /*  1190 */ name = "Burgas";
                    /*  1191 */ break;
                case 40:
                    /*  1193 */ name = "Dobrich";
                    /*  1194 */ break;
                case 41:
                    /*  1196 */ name = "Gabrovo";
                    /*  1197 */ break;
                case 42:
                    /*  1199 */ name = "Grad Sofiya";
                    /*  1200 */ break;
                case 43:
                    /*  1202 */ name = "Khaskovo";
                    /*  1203 */ break;
                case 44:
                    /*  1205 */ name = "Kurdzhali";
                    /*  1206 */ break;
                case 45:
                    /*  1208 */ name = "Kyustendil";
                    /*  1209 */ break;
                case 46:
                    /*  1211 */ name = "Lovech";
                    /*  1212 */ break;
                case 47:
                    /*  1214 */ name = "Montana";
                    /*  1215 */ break;
                case 48:
                    /*  1217 */ name = "Pazardzhik";
                    /*  1218 */ break;
                case 49:
                    /*  1220 */ name = "Pernik";
                    /*  1221 */ break;
                case 50:
                    /*  1223 */ name = "Pleven";
                    /*  1224 */ break;
                case 51:
                    /*  1226 */ name = "Plovdiv";
                    /*  1227 */ break;
                case 52:
                    /*  1229 */ name = "Razgrad";
                    /*  1230 */ break;
                case 53:
                    /*  1232 */ name = "Ruse";
                    /*  1233 */ break;
                case 54:
                    /*  1235 */ name = "Shumen";
                    /*  1236 */ break;
                case 55:
                    /*  1238 */ name = "Silistra";
                    /*  1239 */ break;
                case 56:
                    /*  1241 */ name = "Sliven";
                    /*  1242 */ break;
                case 57:
                    /*  1244 */ name = "Smolyan";
                    /*  1245 */ break;
                case 58:
                    /*  1247 */ name = "Sofiya";
                    /*  1248 */ break;
                case 59:
                    /*  1250 */ name = "Stara Zagora";
                    /*  1251 */ break;
                case 60:
                    /*  1253 */ name = "Turgovishte";
                    /*  1254 */ break;
                case 61:
                    /*  1256 */ name = "Varna";
                    /*  1257 */ break;
                case 62:
                    /*  1259 */ name = "Veliko Turnovo";
                    /*  1260 */ break;
                case 63:
                    /*  1262 */ name = "Vidin";
                    /*  1263 */ break;
                case 64:
                    /*  1265 */ name = "Vratsa";
                    /*  1266 */ break;
                case 65:
                    /*  1268 */ name = "Yambol";
                case 34:
                case 35:
                case 36:
                /*  1272 */ case 37:
            }
        }
        if (country_code.equals("BH") == true) /*  1273 */ {
            switch (region_code2) {
                case 1:
                    /*  1275 */ name = "Al Hadd";
                    /*  1276 */ break;
                case 2:
                    /*  1278 */ name = "Al Manamah";
                    /*  1279 */ break;
                case 5:
                    /*  1281 */ name = "Jidd Hafs";
                    /*  1282 */ break;
                case 6:
                    /*  1284 */ name = "Sitrah";
                    /*  1285 */ break;
                case 8:
                    /*  1287 */ name = "Al Mintaqah al Gharbiyah";
                    /*  1288 */ break;
                case 9:
                    /*  1290 */ name = "Mintaqat Juzur Hawar";
                    /*  1291 */ break;
                case 10:
                    /*  1293 */ name = "Al Mintaqah ash Shamaliyah";
                    /*  1294 */ break;
                case 11:
                    /*  1296 */ name = "Al Mintaqah al Wusta";
                    /*  1297 */ break;
                case 12:
                    /*  1299 */ name = "Madinat";
                    /*  1300 */ break;
                case 13:
                    /*  1302 */ name = "Ar Rifa";
                    /*  1303 */ break;
                case 14:
                    /*  1305 */ name = "Madinat Hamad";
                    /*  1306 */ break;
                case 15:
                    /*  1308 */ name = "Al Muharraq";
                    /*  1309 */ break;
                case 16:
                    /*  1311 */ name = "Al Asimah";
                    /*  1312 */ break;
                case 17:
                    /*  1314 */ name = "Al Janubiyah";
                    /*  1315 */ break;
                case 18:
                    /*  1317 */ name = "Ash Shamaliyah";
                    /*  1318 */ break;
                case 19:
                    /*  1320 */ name = "Al Wusta";
                case 3:
                case 4:
                case 7:
            }
        }
        /*  1324 */ if (country_code.equals("BI") == true) /*  1325 */ {
            switch (region_code2) {
                case 2:
                    /*  1327 */ name = "Bujumbura";
                    /*  1328 */ break;
                case 9:
                    /*  1330 */ name = "Bubanza";
                    /*  1331 */ break;
                case 10:
                    /*  1333 */ name = "Bururi";
                    /*  1334 */ break;
                case 11:
                    /*  1336 */ name = "Cankuzo";
                    /*  1337 */ break;
                case 12:
                    /*  1339 */ name = "Cibitoke";
                    /*  1340 */ break;
                case 13:
                    /*  1342 */ name = "Gitega";
                    /*  1343 */ break;
                case 14:
                    /*  1345 */ name = "Karuzi";
                    /*  1346 */ break;
                case 15:
                    /*  1348 */ name = "Kayanza";
                    /*  1349 */ break;
                case 16:
                    /*  1351 */ name = "Kirundo";
                    /*  1352 */ break;
                case 17:
                    /*  1354 */ name = "Makamba";
                    /*  1355 */ break;
                case 18:
                    /*  1357 */ name = "Muyinga";
                    /*  1358 */ break;
                case 19:
                    /*  1360 */ name = "Ngozi";
                    /*  1361 */ break;
                case 20:
                    /*  1363 */ name = "Rutana";
                    /*  1364 */ break;
                case 21:
                    /*  1366 */ name = "Ruyigi";
                    /*  1367 */ break;
                case 22:
                    /*  1369 */ name = "Muramvya";
                    /*  1370 */ break;
                case 23:
                    /*  1372 */ name = "Mwaro";
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                /*  1376 */ case 8:
            }
        }
        if (country_code.equals("BJ") == true) {
            /*  1377 */ switch (region_code2) {
                case 7:
                    /*  1379 */ name = "Alibori";
                    /*  1380 */ break;
                case 8:
                    /*  1382 */ name = "Atakora";
                    /*  1383 */ break;
                case 9:
                    /*  1385 */ name = "Atlanyique";
                    /*  1386 */ break;
                case 10:
                    /*  1388 */ name = "Borgou";
                    /*  1389 */ break;
                case 11:
                    /*  1391 */ name = "Collines";
                    /*  1392 */ break;
                case 12:
                    /*  1394 */ name = "Kouffo";
                    /*  1395 */ break;
                case 13:
                    /*  1397 */ name = "Donga";
                    /*  1398 */ break;
                case 14:
                    /*  1400 */ name = "Littoral";
                    /*  1401 */ break;
                case 15:
                    /*  1403 */ name = "Mono";
                    /*  1404 */ break;
                case 16:
                    /*  1406 */ name = "Oueme";
                    /*  1407 */ break;
                case 17:
                    /*  1409 */ name = "Plateau";
                    /*  1410 */ break;
                case 18:
                    /*  1412 */ name = "Zou";
            }
        }

        /*  1416 */ if (country_code.equals("BM") == true) {
            /*  1417 */ switch (region_code2) {
                case 1:
                    /*  1419 */ name = "Devonshire";
                    /*  1420 */ break;
                case 2:
                    /*  1422 */ name = "Hamilton";
                    /*  1423 */ break;
                case 3:
                    /*  1425 */ name = "Hamilton";
                    /*  1426 */ break;
                case 4:
                    /*  1428 */ name = "Paget";
                    /*  1429 */ break;
                case 5:
                    /*  1431 */ name = "Pembroke";
                    /*  1432 */ break;
                case 6:
                    /*  1434 */ name = "Saint George";
                    /*  1435 */ break;
                case 7:
                    /*  1437 */ name = "Saint George's";
                    /*  1438 */ break;
                case 8:
                    /*  1440 */ name = "Sandys";
                    /*  1441 */ break;
                case 9:
                    /*  1443 */ name = "Smiths";
                    /*  1444 */ break;
                case 10:
                    /*  1446 */ name = "Southampton";
                    /*  1447 */ break;
                case 11:
                    /*  1449 */ name = "Warwick";
            }
        }

        /*  1453 */ if (country_code.equals("BN") == true) {
            /*  1454 */ switch (region_code2) {
                case 7:
                    /*  1456 */ name = "Alibori";
                    /*  1457 */ break;
                case 8:
                    /*  1459 */ name = "Belait";
                    /*  1460 */ break;
                case 9:
                    /*  1462 */ name = "Brunei and Muara";
                    /*  1463 */ break;
                case 10:
                    /*  1465 */ name = "Temburong";
                    /*  1466 */ break;
                case 11:
                    /*  1468 */ name = "Collines";
                    /*  1469 */ break;
                case 12:
                    /*  1471 */ name = "Kouffo";
                    /*  1472 */ break;
                case 13:
                    /*  1474 */ name = "Donga";
                    /*  1475 */ break;
                case 14:
                    /*  1477 */ name = "Littoral";
                    /*  1478 */ break;
                case 15:
                    /*  1480 */ name = "Tutong";
                    /*  1481 */ break;
                case 16:
                    /*  1483 */ name = "Oueme";
                    /*  1484 */ break;
                case 17:
                    /*  1486 */ name = "Plateau";
                    /*  1487 */ break;
                case 18:
                    /*  1489 */ name = "Zou";
            }
        }

        /*  1493 */ if (country_code.equals("BO") == true) {
            /*  1494 */ switch (region_code2) {
                case 1:
                    /*  1496 */ name = "Chuquisaca";
                    /*  1497 */ break;
                case 2:
                    /*  1499 */ name = "Cochabamba";
                    /*  1500 */ break;
                case 3:
                    /*  1502 */ name = "El Beni";
                    /*  1503 */ break;
                case 4:
                    /*  1505 */ name = "La Paz";
                    /*  1506 */ break;
                case 5:
                    /*  1508 */ name = "Oruro";
                    /*  1509 */ break;
                case 6:
                    /*  1511 */ name = "Pando";
                    /*  1512 */ break;
                case 7:
                    /*  1514 */ name = "Potosi";
                    /*  1515 */ break;
                case 8:
                    /*  1517 */ name = "Santa Cruz";
                    /*  1518 */ break;
                case 9:
                    /*  1520 */ name = "Tarija";
            }
        }

        /*  1524 */ if (country_code.equals("BR") == true) /*  1525 */ {
            switch (region_code2) {
                case 1:
                    /*  1527 */ name = "Acre";
                    /*  1528 */ break;
                case 2:
                    /*  1530 */ name = "Alagoas";
                    /*  1531 */ break;
                case 3:
                    /*  1533 */ name = "Amapa";
                    /*  1534 */ break;
                case 4:
                    /*  1536 */ name = "Amazonas";
                    /*  1537 */ break;
                case 5:
                    /*  1539 */ name = "Bahia";
                    /*  1540 */ break;
                case 6:
                    /*  1542 */ name = "Ceara";
                    /*  1543 */ break;
                case 7:
                    /*  1545 */ name = "Distrito Federal";
                    /*  1546 */ break;
                case 8:
                    /*  1548 */ name = "Espirito Santo";
                    /*  1549 */ break;
                case 11:
                    /*  1551 */ name = "Mato Grosso do Sul";
                    /*  1552 */ break;
                case 13:
                    /*  1554 */ name = "Maranhao";
                    /*  1555 */ break;
                case 14:
                    /*  1557 */ name = "Mato Grosso";
                    /*  1558 */ break;
                case 15:
                    /*  1560 */ name = "Minas Gerais";
                    /*  1561 */ break;
                case 16:
                    /*  1563 */ name = "Para";
                    /*  1564 */ break;
                case 17:
                    /*  1566 */ name = "Paraiba";
                    /*  1567 */ break;
                case 18:
                    /*  1569 */ name = "Parana";
                    /*  1570 */ break;
                case 20:
                    /*  1572 */ name = "Piaui";
                    /*  1573 */ break;
                case 21:
                    /*  1575 */ name = "Rio de Janeiro";
                    /*  1576 */ break;
                case 22:
                    /*  1578 */ name = "Rio Grande do Norte";
                    /*  1579 */ break;
                case 23:
                    /*  1581 */ name = "Rio Grande do Sul";
                    /*  1582 */ break;
                case 24:
                    /*  1584 */ name = "Rondonia";
                    /*  1585 */ break;
                case 25:
                    /*  1587 */ name = "Roraima";
                    /*  1588 */ break;
                case 26:
                    /*  1590 */ name = "Santa Catarina";
                    /*  1591 */ break;
                case 27:
                    /*  1593 */ name = "Sao Paulo";
                    /*  1594 */ break;
                case 28:
                    /*  1596 */ name = "Sergipe";
                    /*  1597 */ break;
                case 29:
                    /*  1599 */ name = "Goias";
                    /*  1600 */ break;
                case 30:
                    /*  1602 */ name = "Pernambuco";
                    /*  1603 */ break;
                case 31:
                    /*  1605 */ name = "Tocantins";
                case 9:
                case 10:
                case 12:
                /*  1609 */ case 19:
            }
        }
        if (country_code.equals("BS") == true) /*  1610 */ {
            switch (region_code2) {
                case 5:
                    /*  1612 */ name = "Bimini";
                    /*  1613 */ break;
                case 6:
                    /*  1615 */ name = "Cat Island";
                    /*  1616 */ break;
                case 10:
                    /*  1618 */ name = "Exuma";
                    /*  1619 */ break;
                case 13:
                    /*  1621 */ name = "Inagua";
                    /*  1622 */ break;
                case 15:
                    /*  1624 */ name = "Long Island";
                    /*  1625 */ break;
                case 16:
                    /*  1627 */ name = "Mayaguana";
                    /*  1628 */ break;
                case 18:
                    /*  1630 */ name = "Ragged Island";
                    /*  1631 */ break;
                case 22:
                    /*  1633 */ name = "Harbour Island";
                    /*  1634 */ break;
                case 23:
                    /*  1636 */ name = "New Providence";
                    /*  1637 */ break;
                case 24:
                    /*  1639 */ name = "Acklins and Crooked Islands";
                    /*  1640 */ break;
                case 25:
                    /*  1642 */ name = "Freeport";
                    /*  1643 */ break;
                case 26:
                    /*  1645 */ name = "Fresh Creek";
                    /*  1646 */ break;
                case 27:
                    /*  1648 */ name = "Governor's Harbour";
                    /*  1649 */ break;
                case 28:
                    /*  1651 */ name = "Green Turtle Cay";
                    /*  1652 */ break;
                case 29:
                    /*  1654 */ name = "High Rock";
                    /*  1655 */ break;
                case 30:
                    /*  1657 */ name = "Kemps Bay";
                    /*  1658 */ break;
                case 31:
                    /*  1660 */ name = "Marsh Harbour";
                    /*  1661 */ break;
                case 32:
                    /*  1663 */ name = "Nichollstown and Berry Islands";
                    /*  1664 */ break;
                case 33:
                    /*  1666 */ name = "Rock Sound";
                    /*  1667 */ break;
                case 34:
                    /*  1669 */ name = "Sandy Point";
                    /*  1670 */ break;
                case 35:
                    /*  1672 */ name = "San Salvador and Rum Cay";
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                case 14:
                case 17:
                case 19:
                case 20:
                /*  1676 */ case 21:
            }
        }
        if (country_code.equals("BT") == true) {
            /*  1677 */ switch (region_code2) {
                case 5:
                    /*  1679 */ name = "Bumthang";
                    /*  1680 */ break;
                case 6:
                    /*  1682 */ name = "Chhukha";
                    /*  1683 */ break;
                case 7:
                    /*  1685 */ name = "Chirang";
                    /*  1686 */ break;
                case 8:
                    /*  1688 */ name = "Daga";
                    /*  1689 */ break;
                case 9:
                    /*  1691 */ name = "Geylegphug";
                    /*  1692 */ break;
                case 10:
                    /*  1694 */ name = "Ha";
                    /*  1695 */ break;
                case 11:
                    /*  1697 */ name = "Lhuntshi";
                    /*  1698 */ break;
                case 12:
                    /*  1700 */ name = "Mongar";
                    /*  1701 */ break;
                case 13:
                    /*  1703 */ name = "Paro";
                    /*  1704 */ break;
                case 14:
                    /*  1706 */ name = "Pemagatsel";
                    /*  1707 */ break;
                case 15:
                    /*  1709 */ name = "Punakha";
                    /*  1710 */ break;
                case 16:
                    /*  1712 */ name = "Samchi";
                    /*  1713 */ break;
                case 17:
                    /*  1715 */ name = "Samdrup";
                    /*  1716 */ break;
                case 18:
                    /*  1718 */ name = "Shemgang";
                    /*  1719 */ break;
                case 19:
                    /*  1721 */ name = "Tashigang";
                    /*  1722 */ break;
                case 20:
                    /*  1724 */ name = "Thimphu";
                    /*  1725 */ break;
                case 21:
                    /*  1727 */ name = "Tongsa";
                    /*  1728 */ break;
                case 22:
                    /*  1730 */ name = "Wangdi Phodrang";
            }
        }

        /*  1734 */ if (country_code.equals("BW") == true) /*  1735 */ {
            switch (region_code2) {
                case 1:
                    /*  1737 */ name = "Central";
                    /*  1738 */ break;
                case 3:
                    /*  1740 */ name = "Ghanzi";
                    /*  1741 */ break;
                case 4:
                    /*  1743 */ name = "Kgalagadi";
                    /*  1744 */ break;
                case 5:
                    /*  1746 */ name = "Kgatleng";
                    /*  1747 */ break;
                case 6:
                    /*  1749 */ name = "Kweneng";
                    /*  1750 */ break;
                case 8:
                    /*  1752 */ name = "North-East";
                    /*  1753 */ break;
                case 9:
                    /*  1755 */ name = "South-East";
                    /*  1756 */ break;
                case 10:
                    /*  1758 */ name = "Southern";
                    /*  1759 */ break;
                case 11:
                    /*  1761 */ name = "North-West";
                case 2:
                case 7:
            }
        }
        /*  1765 */ if (country_code.equals("BY") == true) {
            /*  1766 */ switch (region_code2) {
                case 1:
                    /*  1768 */ name = "Brestskaya Voblasts'";
                    /*  1769 */ break;
                case 2:
                    /*  1771 */ name = "Homyel'skaya Voblasts'";
                    /*  1772 */ break;
                case 3:
                    /*  1774 */ name = "Hrodzyenskaya Voblasts'";
                    /*  1775 */ break;
                case 4:
                    /*  1777 */ name = "Minsk";
                    /*  1778 */ break;
                case 5:
                    /*  1780 */ name = "Minskaya Voblasts'";
                    /*  1781 */ break;
                case 6:
                    /*  1783 */ name = "Mahilyowskaya Voblasts'";
                    /*  1784 */ break;
                case 7:
                    /*  1786 */ name = "Vitsyebskaya Voblasts'";
            }
        }

        /*  1790 */ if (country_code.equals("BZ") == true) {
            /*  1791 */ switch (region_code2) {
                case 1:
                    /*  1793 */ name = "Belize";
                    /*  1794 */ break;
                case 2:
                    /*  1796 */ name = "Cayo";
                    /*  1797 */ break;
                case 3:
                    /*  1799 */ name = "Corozal";
                    /*  1800 */ break;
                case 4:
                    /*  1802 */ name = "Orange Walk";
                    /*  1803 */ break;
                case 5:
                    /*  1805 */ name = "Stann Creek";
                    /*  1806 */ break;
                case 6:
                    /*  1808 */ name = "Toledo";
            }
        }

        /*  1812 */ if (country_code.equals("CD") == true) /*  1813 */ {
            switch (region_code2) {
                case 1:
                    /*  1815 */ name = "Bandundu";
                    /*  1816 */ break;
                case 2:
                    /*  1818 */ name = "Equateur";
                    /*  1819 */ break;
                case 4:
                    /*  1821 */ name = "Kasai-Oriental";
                    /*  1822 */ break;
                case 5:
                    /*  1824 */ name = "Katanga";
                    /*  1825 */ break;
                case 6:
                    /*  1827 */ name = "Kinshasa";
                    /*  1828 */ break;
                case 8:
                    /*  1830 */ name = "Bas-Congo";
                    /*  1831 */ break;
                case 9:
                    /*  1833 */ name = "Orientale";
                    /*  1834 */ break;
                case 10:
                    /*  1836 */ name = "Maniema";
                    /*  1837 */ break;
                case 11:
                    /*  1839 */ name = "Nord-Kivu";
                    /*  1840 */ break;
                case 12:
                    /*  1842 */ name = "Sud-Kivu";
                case 3:
                case 7:
            }
        }
        /*  1846 */ if (country_code.equals("CF") == true) {
            /*  1847 */ switch (region_code2) {
                case 1:
                    /*  1849 */ name = "Bamingui-Bangoran";
                    /*  1850 */ break;
                case 2:
                    /*  1852 */ name = "Basse-Kotto";
                    /*  1853 */ break;
                case 3:
                    /*  1855 */ name = "Haute-Kotto";
                    /*  1856 */ break;
                case 4:
                    /*  1858 */ name = "Mambere-Kadei";
                    /*  1859 */ break;
                case 5:
                    /*  1861 */ name = "Haut-Mbomou";
                    /*  1862 */ break;
                case 6:
                    /*  1864 */ name = "Kemo";
                    /*  1865 */ break;
                case 7:
                    /*  1867 */ name = "Lobaye";
                    /*  1868 */ break;
                case 8:
                    /*  1870 */ name = "Mbomou";
                    /*  1871 */ break;
                case 9:
                    /*  1873 */ name = "Nana-Mambere";
                    /*  1874 */ break;
                case 11:
                    /*  1876 */ name = "Ouaka";
                    /*  1877 */ break;
                case 12:
                    /*  1879 */ name = "Ouham";
                    /*  1880 */ break;
                case 13:
                    /*  1882 */ name = "Ouham-Pende";
                    /*  1883 */ break;
                case 14:
                    /*  1885 */ name = "Cuvette-Ouest";
                    /*  1886 */ break;
                case 15:
                    /*  1888 */ name = "Nana-Grebizi";
                    /*  1889 */ break;
                case 16:
                    /*  1891 */ name = "Sangha-Mbaere";
                    /*  1892 */ break;
                case 17:
                    /*  1894 */ name = "Ombella-Mpoko";
                    /*  1895 */ break;
                case 18:
                    /*  1897 */ name = "Bangui";
                case 10:
            }
        }
        /*  1901 */ if (country_code.equals("CG") == true) /*  1902 */ {
            switch (region_code2) {
                case 1:
                    /*  1904 */ name = "Bouenza";
                    /*  1905 */ break;
                case 4:
                    /*  1907 */ name = "Kouilou";
                    /*  1908 */ break;
                case 5:
                    /*  1910 */ name = "Lekoumou";
                    /*  1911 */ break;
                case 6:
                    /*  1913 */ name = "Likouala";
                    /*  1914 */ break;
                case 7:
                    /*  1916 */ name = "Niari";
                    /*  1917 */ break;
                case 8:
                    /*  1919 */ name = "Plateaux";
                    /*  1920 */ break;
                case 10:
                    /*  1922 */ name = "Sangha";
                    /*  1923 */ break;
                case 11:
                    /*  1925 */ name = "Pool";
                    /*  1926 */ break;
                case 12:
                    /*  1928 */ name = "Brazzaville";
                    /*  1929 */ break;
                case 13:
                    /*  1931 */ name = "Cuvette";
                    /*  1932 */ break;
                case 14:
                    /*  1934 */ name = "Cuvette-Ouest";
                case 2:
                case 3:
                case 9:
            }
        }
        /*  1938 */ if (country_code.equals("CH") == true) {
            /*  1939 */ switch (region_code2) {
                case 1:
                    /*  1941 */ name = "Aargau";
                    /*  1942 */ break;
                case 2:
                    /*  1944 */ name = "Ausser-Rhoden";
                    /*  1945 */ break;
                case 3:
                    /*  1947 */ name = "Basel-Landschaft";
                    /*  1948 */ break;
                case 4:
                    /*  1950 */ name = "Basel-Stadt";
                    /*  1951 */ break;
                case 5:
                    /*  1953 */ name = "Bern";
                    /*  1954 */ break;
                case 6:
                    /*  1956 */ name = "Fribourg";
                    /*  1957 */ break;
                case 7:
                    /*  1959 */ name = "Geneve";
                    /*  1960 */ break;
                case 8:
                    /*  1962 */ name = "Glarus";
                    /*  1963 */ break;
                case 9:
                    /*  1965 */ name = "Graubunden";
                    /*  1966 */ break;
                case 10:
                    /*  1968 */ name = "Inner-Rhoden";
                    /*  1969 */ break;
                case 11:
                    /*  1971 */ name = "Luzern";
                    /*  1972 */ break;
                case 12:
                    /*  1974 */ name = "Neuchatel";
                    /*  1975 */ break;
                case 13:
                    /*  1977 */ name = "Nidwalden";
                    /*  1978 */ break;
                case 14:
                    /*  1980 */ name = "Obwalden";
                    /*  1981 */ break;
                case 15:
                    /*  1983 */ name = "Sankt Gallen";
                    /*  1984 */ break;
                case 16:
                    /*  1986 */ name = "Schaffhausen";
                    /*  1987 */ break;
                case 17:
                    /*  1989 */ name = "Schwyz";
                    /*  1990 */ break;
                case 18:
                    /*  1992 */ name = "Solothurn";
                    /*  1993 */ break;
                case 19:
                    /*  1995 */ name = "Thurgau";
                    /*  1996 */ break;
                case 20:
                    /*  1998 */ name = "Ticino";
                    /*  1999 */ break;
                case 21:
                    /*  2001 */ name = "Uri";
                    /*  2002 */ break;
                case 22:
                    /*  2004 */ name = "Valais";
                    /*  2005 */ break;
                case 23:
                    /*  2007 */ name = "Vaud";
                    /*  2008 */ break;
                case 24:
                    /*  2010 */ name = "Zug";
                    /*  2011 */ break;
                case 25:
                    /*  2013 */ name = "Zurich";
                    /*  2014 */ break;
                case 26:
                    /*  2016 */ name = "Jura";
            }
        }

        /*  2020 */ if (country_code.equals("CI") == true) {
            /*  2021 */ switch (region_code2) {
                case 74:
                    /*  2023 */ name = "Agneby";
                    /*  2024 */ break;
                case 75:
                    /*  2026 */ name = "Bafing";
                    /*  2027 */ break;
                case 76:
                    /*  2029 */ name = "Bas-Sassandra";
                    /*  2030 */ break;
                case 77:
                    /*  2032 */ name = "Denguele";
                    /*  2033 */ break;
                case 78:
                    /*  2035 */ name = "Dix-Huit Montagnes";
                    /*  2036 */ break;
                case 79:
                    /*  2038 */ name = "Fromager";
                    /*  2039 */ break;
                case 80:
                    /*  2041 */ name = "Haut-Sassandra";
                    /*  2042 */ break;
                case 81:
                    /*  2044 */ name = "Lacs";
                    /*  2045 */ break;
                case 82:
                    /*  2047 */ name = "Lagunes";
                    /*  2048 */ break;
                case 83:
                    /*  2050 */ name = "Marahoue";
                    /*  2051 */ break;
                case 84:
                    /*  2053 */ name = "Moyen-Cavally";
                    /*  2054 */ break;
                case 85:
                    /*  2056 */ name = "Moyen-Comoe";
                    /*  2057 */ break;
                case 86:
                    /*  2059 */ name = "N'zi-Comoe";
                    /*  2060 */ break;
                case 87:
                    /*  2062 */ name = "Savanes";
                    /*  2063 */ break;
                case 88:
                    /*  2065 */ name = "Sud-Bandama";
                    /*  2066 */ break;
                case 89:
                    /*  2068 */ name = "Sud-Comoe";
                    /*  2069 */ break;
                case 90:
                    /*  2071 */ name = "Vallee du Bandama";
                    /*  2072 */ break;
                case 91:
                    /*  2074 */ name = "Worodougou";
                    /*  2075 */ break;
                case 92:
                    /*  2077 */ name = "Zanzan";
            }
        }

        /*  2081 */ if (country_code.equals("CL") == true) {
            /*  2082 */ switch (region_code2) {
                case 1:
                    /*  2084 */ name = "Valparaiso";
                    /*  2085 */ break;
                case 2:
                    /*  2087 */ name = "Aisen del General Carlos Ibanez del Campo";
                    /*  2088 */ break;
                case 3:
                    /*  2090 */ name = "Antofagasta";
                    /*  2091 */ break;
                case 4:
                    /*  2093 */ name = "Araucania";
                    /*  2094 */ break;
                case 5:
                    /*  2096 */ name = "Atacama";
                    /*  2097 */ break;
                case 6:
                    /*  2099 */ name = "Bio-Bio";
                    /*  2100 */ break;
                case 7:
                    /*  2102 */ name = "Coquimbo";
                    /*  2103 */ break;
                case 8:
                    /*  2105 */ name = "Libertador General Bernardo O'Higgins";
                    /*  2106 */ break;
                case 9:
                    /*  2108 */ name = "Los Lagos";
                    /*  2109 */ break;
                case 10:
                    /*  2111 */ name = "Magallanes y de la Antartica Chilena";
                    /*  2112 */ break;
                case 11:
                    /*  2114 */ name = "Maule";
                    /*  2115 */ break;
                case 12:
                    /*  2117 */ name = "Region Metropolitana";
                    /*  2118 */ break;
                case 13:
                    /*  2120 */ name = "Tarapaca";
                    /*  2121 */ break;
                case 14:
                    /*  2123 */ name = "Los Lagos";
                    /*  2124 */ break;
                case 15:
                    /*  2126 */ name = "Tarapaca";
                    /*  2127 */ break;
                case 16:
                    /*  2129 */ name = "Arica y Parinacota";
                    /*  2130 */ break;
                case 17:
                    /*  2132 */ name = "Los Rios";
            }
        }

        /*  2136 */ if (country_code.equals("CM") == true) {
            /*  2137 */ switch (region_code2) {
                case 4:
                    /*  2139 */ name = "Est";
                    /*  2140 */ break;
                case 5:
                    /*  2142 */ name = "Littoral";
                    /*  2143 */ break;
                case 7:
                    /*  2145 */ name = "Nord-Ouest";
                    /*  2146 */ break;
                case 8:
                    /*  2148 */ name = "Ouest";
                    /*  2149 */ break;
                case 9:
                    /*  2151 */ name = "Sud-Ouest";
                    /*  2152 */ break;
                case 10:
                    /*  2154 */ name = "Adamaoua";
                    /*  2155 */ break;
                case 11:
                    /*  2157 */ name = "Centre";
                    /*  2158 */ break;
                case 12:
                    /*  2160 */ name = "Extreme-Nord";
                    /*  2161 */ break;
                case 13:
                    /*  2163 */ name = "Nord";
                    /*  2164 */ break;
                case 14:
                    /*  2166 */ name = "Sud";
                case 6:
            }
        }
        /*  2170 */ if (country_code.equals("CN") == true) /*  2171 */ {
            switch (region_code2) {
                case 1:
                    /*  2173 */ name = "Anhui";
                    /*  2174 */ break;
                case 2:
                    /*  2176 */ name = "Zhejiang";
                    /*  2177 */ break;
                case 3:
                    /*  2179 */ name = "Jiangxi";
                    /*  2180 */ break;
                case 4:
                    /*  2182 */ name = "Jiangsu";
                    /*  2183 */ break;
                case 5:
                    /*  2185 */ name = "Jilin";
                    /*  2186 */ break;
                case 6:
                    /*  2188 */ name = "Qinghai";
                    /*  2189 */ break;
                case 7:
                    /*  2191 */ name = "Fujian";
                    /*  2192 */ break;
                case 8:
                    /*  2194 */ name = "Heilongjiang";
                    /*  2195 */ break;
                case 9:
                    /*  2197 */ name = "Henan";
                    /*  2198 */ break;
                case 10:
                    /*  2200 */ name = "Hebei";
                    /*  2201 */ break;
                case 11:
                    /*  2203 */ name = "Hunan";
                    /*  2204 */ break;
                case 12:
                    /*  2206 */ name = "Hubei";
                    /*  2207 */ break;
                case 13:
                    /*  2209 */ name = "Xinjiang";
                    /*  2210 */ break;
                case 14:
                    /*  2212 */ name = "Xizang";
                    /*  2213 */ break;
                case 15:
                    /*  2215 */ name = "Gansu";
                    /*  2216 */ break;
                case 16:
                    /*  2218 */ name = "Guangxi";
                    /*  2219 */ break;
                case 18:
                    /*  2221 */ name = "Guizhou";
                    /*  2222 */ break;
                case 19:
                    /*  2224 */ name = "Liaoning";
                    /*  2225 */ break;
                case 20:
                    /*  2227 */ name = "Nei Mongol";
                    /*  2228 */ break;
                case 21:
                    /*  2230 */ name = "Ningxia";
                    /*  2231 */ break;
                case 22:
                    /*  2233 */ name = "Beijing";
                    /*  2234 */ break;
                case 23:
                    /*  2236 */ name = "Shanghai";
                    /*  2237 */ break;
                case 24:
                    /*  2239 */ name = "Shanxi";
                    /*  2240 */ break;
                case 25:
                    /*  2242 */ name = "Shandong";
                    /*  2243 */ break;
                case 26:
                    /*  2245 */ name = "Shaanxi";
                    /*  2246 */ break;
                case 28:
                    /*  2248 */ name = "Tianjin";
                    /*  2249 */ break;
                case 29:
                    /*  2251 */ name = "Yunnan";
                    /*  2252 */ break;
                case 30:
                    /*  2254 */ name = "Guangdong";
                    /*  2255 */ break;
                case 31:
                    /*  2257 */ name = "Hainan";
                    /*  2258 */ break;
                case 32:
                    /*  2260 */ name = "Sichuan";
                    /*  2261 */ break;
                case 33:
                    /*  2263 */ name = "Chongqing";
                case 17:
                case 27:
            }
        }
        /*  2267 */ if (country_code.equals("CO") == true) /*  2268 */ {
            switch (region_code2) {
                case 1:
                    /*  2270 */ name = "Amazonas";
                    /*  2271 */ break;
                case 2:
                    /*  2273 */ name = "Antioquia";
                    /*  2274 */ break;
                case 3:
                    /*  2276 */ name = "Arauca";
                    /*  2277 */ break;
                case 4:
                    /*  2279 */ name = "Atlantico";
                    /*  2280 */ break;
                case 8:
                    /*  2282 */ name = "Caqueta";
                    /*  2283 */ break;
                case 9:
                    /*  2285 */ name = "Cauca";
                    /*  2286 */ break;
                case 10:
                    /*  2288 */ name = "Cesar";
                    /*  2289 */ break;
                case 11:
                    /*  2291 */ name = "Choco";
                    /*  2292 */ break;
                case 12:
                    /*  2294 */ name = "Cordoba";
                    /*  2295 */ break;
                case 14:
                    /*  2297 */ name = "Guaviare";
                    /*  2298 */ break;
                case 15:
                    /*  2300 */ name = "Guainia";
                    /*  2301 */ break;
                case 16:
                    /*  2303 */ name = "Huila";
                    /*  2304 */ break;
                case 17:
                    /*  2306 */ name = "La Guajira";
                    /*  2307 */ break;
                case 19:
                    /*  2309 */ name = "Meta";
                    /*  2310 */ break;
                case 20:
                    /*  2312 */ name = "Narino";
                    /*  2313 */ break;
                case 21:
                    /*  2315 */ name = "Norte de Santander";
                    /*  2316 */ break;
                case 22:
                    /*  2318 */ name = "Putumayo";
                    /*  2319 */ break;
                case 23:
                    /*  2321 */ name = "Quindio";
                    /*  2322 */ break;
                case 24:
                    /*  2324 */ name = "Risaralda";
                    /*  2325 */ break;
                case 25:
                    /*  2327 */ name = "San Andres y Providencia";
                    /*  2328 */ break;
                case 26:
                    /*  2330 */ name = "Santander";
                    /*  2331 */ break;
                case 27:
                    /*  2333 */ name = "Sucre";
                    /*  2334 */ break;
                case 28:
                    /*  2336 */ name = "Tolima";
                    /*  2337 */ break;
                case 29:
                    /*  2339 */ name = "Valle del Cauca";
                    /*  2340 */ break;
                case 30:
                    /*  2342 */ name = "Vaupes";
                    /*  2343 */ break;
                case 31:
                    /*  2345 */ name = "Vichada";
                    /*  2346 */ break;
                case 32:
                    /*  2348 */ name = "Casanare";
                    /*  2349 */ break;
                case 33:
                    /*  2351 */ name = "Cundinamarca";
                    /*  2352 */ break;
                case 34:
                    /*  2354 */ name = "Distrito Especial";
                    /*  2355 */ break;
                case 35:
                    /*  2357 */ name = "Bolivar";
                    /*  2358 */ break;
                case 36:
                    /*  2360 */ name = "Boyaca";
                    /*  2361 */ break;
                case 37:
                    /*  2363 */ name = "Caldas";
                    /*  2364 */ break;
                case 38:
                    /*  2366 */ name = "Magdalena";
                case 5:
                case 6:
                case 7:
                case 13:
                /*  2370 */ case 18:
            }
        }
        if (country_code.equals("CR") == true) {
            /*  2371 */ switch (region_code2) {
                case 1:
                    /*  2373 */ name = "Alajuela";
                    /*  2374 */ break;
                case 2:
                    /*  2376 */ name = "Cartago";
                    /*  2377 */ break;
                case 3:
                    /*  2379 */ name = "Guanacaste";
                    /*  2380 */ break;
                case 4:
                    /*  2382 */ name = "Heredia";
                    /*  2383 */ break;
                case 6:
                    /*  2385 */ name = "Limon";
                    /*  2386 */ break;
                case 7:
                    /*  2388 */ name = "Puntarenas";
                    /*  2389 */ break;
                case 8:
                    /*  2391 */ name = "San Jose";
                case 5:
            }
        }
        /*  2395 */ if (country_code.equals("CU") == true) {
            /*  2396 */ switch (region_code2) {
                case 1:
                    /*  2398 */ name = "Pinar del Rio";
                    /*  2399 */ break;
                case 2:
                    /*  2401 */ name = "Ciudad de la Habana";
                    /*  2402 */ break;
                case 3:
                    /*  2404 */ name = "Matanzas";
                    /*  2405 */ break;
                case 4:
                    /*  2407 */ name = "Isla de la Juventud";
                    /*  2408 */ break;
                case 5:
                    /*  2410 */ name = "Camaguey";
                    /*  2411 */ break;
                case 7:
                    /*  2413 */ name = "Ciego de Avila";
                    /*  2414 */ break;
                case 8:
                    /*  2416 */ name = "Cienfuegos";
                    /*  2417 */ break;
                case 9:
                    /*  2419 */ name = "Granma";
                    /*  2420 */ break;
                case 10:
                    /*  2422 */ name = "Guantanamo";
                    /*  2423 */ break;
                case 11:
                    /*  2425 */ name = "La Habana";
                    /*  2426 */ break;
                case 12:
                    /*  2428 */ name = "Holguin";
                    /*  2429 */ break;
                case 13:
                    /*  2431 */ name = "Las Tunas";
                    /*  2432 */ break;
                case 14:
                    /*  2434 */ name = "Sancti Spiritus";
                    /*  2435 */ break;
                case 15:
                    /*  2437 */ name = "Santiago de Cuba";
                    /*  2438 */ break;
                case 16:
                    /*  2440 */ name = "Villa Clara";
                case 6:
            }
        }
        /*  2444 */ if (country_code.equals("CV") == true) /*  2445 */ {
            switch (region_code2) {
                case 1:
                    /*  2447 */ name = "Boa Vista";
                    /*  2448 */ break;
                case 2:
                    /*  2450 */ name = "Brava";
                    /*  2451 */ break;
                case 4:
                    /*  2453 */ name = "Maio";
                    /*  2454 */ break;
                case 5:
                    /*  2456 */ name = "Paul";
                    /*  2457 */ break;
                case 7:
                    /*  2459 */ name = "Ribeira Grande";
                    /*  2460 */ break;
                case 8:
                    /*  2462 */ name = "Sal";
                    /*  2463 */ break;
                case 10:
                    /*  2465 */ name = "Sao Nicolau";
                    /*  2466 */ break;
                case 11:
                    /*  2468 */ name = "Sao Vicente";
                    /*  2469 */ break;
                case 13:
                    /*  2471 */ name = "Mosteiros";
                    /*  2472 */ break;
                case 14:
                    /*  2474 */ name = "Praia";
                    /*  2475 */ break;
                case 15:
                    /*  2477 */ name = "Santa Catarina";
                    /*  2478 */ break;
                case 16:
                    /*  2480 */ name = "Santa Cruz";
                    /*  2481 */ break;
                case 17:
                    /*  2483 */ name = "Sao Domingos";
                    /*  2484 */ break;
                case 18:
                    /*  2486 */ name = "Sao Filipe";
                    /*  2487 */ break;
                case 19:
                    /*  2489 */ name = "Sao Miguel";
                    /*  2490 */ break;
                case 20:
                    /*  2492 */ name = "Tarrafal";
                case 3:
                case 6:
                case 9:
                /*  2496 */ case 12:
            }
        }
        if (country_code.equals("CY") == true) {
            /*  2497 */ switch (region_code2) {
                case 1:
                    /*  2499 */ name = "Famagusta";
                    /*  2500 */ break;
                case 2:
                    /*  2502 */ name = "Kyrenia";
                    /*  2503 */ break;
                case 3:
                    /*  2505 */ name = "Larnaca";
                    /*  2506 */ break;
                case 4:
                    /*  2508 */ name = "Nicosia";
                    /*  2509 */ break;
                case 5:
                    /*  2511 */ name = "Limassol";
                    /*  2512 */ break;
                case 6:
                    /*  2514 */ name = "Paphos";
            }
        }

        /*  2518 */ if (country_code.equals("CZ") == true) /*  2519 */ {
            switch (region_code2) {
                case 52:
                    /*  2521 */ name = "Hlavni mesto Praha";
                    /*  2522 */ break;
                case 78:
                    /*  2524 */ name = "Jihomoravsky kraj";
                    /*  2525 */ break;
                case 79:
                    /*  2527 */ name = "Jihocesky kraj";
                    /*  2528 */ break;
                case 80:
                    /*  2530 */ name = "Vysocina";
                    /*  2531 */ break;
                case 81:
                    /*  2533 */ name = "Karlovarsky kraj";
                    /*  2534 */ break;
                case 82:
                    /*  2536 */ name = "Kralovehradecky kraj";
                    /*  2537 */ break;
                case 83:
                    /*  2539 */ name = "Liberecky kraj";
                    /*  2540 */ break;
                case 84:
                    /*  2542 */ name = "Olomoucky kraj";
                    /*  2543 */ break;
                case 85:
                    /*  2545 */ name = "Moravskoslezsky kraj";
                    /*  2546 */ break;
                case 86:
                    /*  2548 */ name = "Pardubicky kraj";
                    /*  2549 */ break;
                case 87:
                    /*  2551 */ name = "Plzensky kraj";
                    /*  2552 */ break;
                case 88:
                    /*  2554 */ name = "Stredocesky kraj";
                    /*  2555 */ break;
                case 89:
                    /*  2557 */ name = "Ustecky kraj";
                    /*  2558 */ break;
                case 90:
                    /*  2560 */ name = "Zlinsky kraj";
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                /*  2564 */ case 77:
            }
        }
        if (country_code.equals("DE") == true) {
            /*  2565 */ switch (region_code2) {
                case 1:
                    /*  2567 */ name = "Baden-Wurttemberg";
                    /*  2568 */ break;
                case 2:
                    /*  2570 */ name = "Bayern";
                    /*  2571 */ break;
                case 3:
                    /*  2573 */ name = "Bremen";
                    /*  2574 */ break;
                case 4:
                    /*  2576 */ name = "Hamburg";
                    /*  2577 */ break;
                case 5:
                    /*  2579 */ name = "Hessen";
                    /*  2580 */ break;
                case 6:
                    /*  2582 */ name = "Niedersachsen";
                    /*  2583 */ break;
                case 7:
                    /*  2585 */ name = "Nordrhein-Westfalen";
                    /*  2586 */ break;
                case 8:
                    /*  2588 */ name = "Rheinland-Pfalz";
                    /*  2589 */ break;
                case 9:
                    /*  2591 */ name = "Saarland";
                    /*  2592 */ break;
                case 10:
                    /*  2594 */ name = "Schleswig-Holstein";
                    /*  2595 */ break;
                case 11:
                    /*  2597 */ name = "Brandenburg";
                    /*  2598 */ break;
                case 12:
                    /*  2600 */ name = "Mecklenburg-Vorpommern";
                    /*  2601 */ break;
                case 13:
                    /*  2603 */ name = "Sachsen";
                    /*  2604 */ break;
                case 14:
                    /*  2606 */ name = "Sachsen-Anhalt";
                    /*  2607 */ break;
                case 15:
                    /*  2609 */ name = "Thuringen";
                    /*  2610 */ break;
                case 16:
                    /*  2612 */ name = "Berlin";
            }
        }

        /*  2616 */ if (country_code.equals("DJ") == true) /*  2617 */ {
            switch (region_code2) {
                case 1:
                    /*  2619 */ name = "Ali Sabieh";
                    /*  2620 */ break;
                case 4:
                    /*  2622 */ name = "Obock";
                    /*  2623 */ break;
                case 5:
                    /*  2625 */ name = "Tadjoura";
                    /*  2626 */ break;
                case 6:
                    /*  2628 */ name = "Dikhil";
                    /*  2629 */ break;
                case 7:
                    /*  2631 */ name = "Djibouti";
                    /*  2632 */ break;
                case 8:
                    /*  2634 */ name = "Arta";
                case 2:
                case 3:
            }
        }
        /*  2638 */ if (country_code.equals("DK") == true) {
            /*  2639 */ switch (region_code2) {
                case 17:
                    /*  2641 */ name = "Hovedstaden";
                    /*  2642 */ break;
                case 18:
                    /*  2644 */ name = "Midtjylland";
                    /*  2645 */ break;
                case 19:
                    /*  2647 */ name = "Nordjylland";
                    /*  2648 */ break;
                case 20:
                    /*  2650 */ name = "Sjelland";
                    /*  2651 */ break;
                case 21:
                    /*  2653 */ name = "Syddanmark";
            }
        }

        /*  2657 */ if (country_code.equals("DM") == true) {
            /*  2658 */ switch (region_code2) {
                case 2:
                    /*  2660 */ name = "Saint Andrew";
                    /*  2661 */ break;
                case 3:
                    /*  2663 */ name = "Saint David";
                    /*  2664 */ break;
                case 4:
                    /*  2666 */ name = "Saint George";
                    /*  2667 */ break;
                case 5:
                    /*  2669 */ name = "Saint John";
                    /*  2670 */ break;
                case 6:
                    /*  2672 */ name = "Saint Joseph";
                    /*  2673 */ break;
                case 7:
                    /*  2675 */ name = "Saint Luke";
                    /*  2676 */ break;
                case 8:
                    /*  2678 */ name = "Saint Mark";
                    /*  2679 */ break;
                case 9:
                    /*  2681 */ name = "Saint Patrick";
                    /*  2682 */ break;
                case 10:
                    /*  2684 */ name = "Saint Paul";
                    /*  2685 */ break;
                case 11:
                    /*  2687 */ name = "Saint Peter";
            }
        }

        /*  2691 */ if (country_code.equals("DO") == true) /*  2692 */ {
            switch (region_code2) {
                case 1:
                    /*  2694 */ name = "Azua";
                    /*  2695 */ break;
                case 2:
                    /*  2697 */ name = "Baoruco";
                    /*  2698 */ break;
                case 3:
                    /*  2700 */ name = "Barahona";
                    /*  2701 */ break;
                case 4:
                    /*  2703 */ name = "Dajabon";
                    /*  2704 */ break;
                case 5:
                    /*  2706 */ name = "Distrito Nacional";
                    /*  2707 */ break;
                case 6:
                    /*  2709 */ name = "Duarte";
                    /*  2710 */ break;
                case 8:
                    /*  2712 */ name = "Espaillat";
                    /*  2713 */ break;
                case 9:
                    /*  2715 */ name = "Independencia";
                    /*  2716 */ break;
                case 10:
                    /*  2718 */ name = "La Altagracia";
                    /*  2719 */ break;
                case 11:
                    /*  2721 */ name = "Elias Pina";
                    /*  2722 */ break;
                case 12:
                    /*  2724 */ name = "La Romana";
                    /*  2725 */ break;
                case 14:
                    /*  2727 */ name = "Maria Trinidad Sanchez";
                    /*  2728 */ break;
                case 15:
                    /*  2730 */ name = "Monte Cristi";
                    /*  2731 */ break;
                case 16:
                    /*  2733 */ name = "Pedernales";
                    /*  2734 */ break;
                case 17:
                    /*  2736 */ name = "Peravia";
                    /*  2737 */ break;
                case 18:
                    /*  2739 */ name = "Puerto Plata";
                    /*  2740 */ break;
                case 19:
                    /*  2742 */ name = "Salcedo";
                    /*  2743 */ break;
                case 20:
                    /*  2745 */ name = "Samana";
                    /*  2746 */ break;
                case 21:
                    /*  2748 */ name = "Sanchez Ramirez";
                    /*  2749 */ break;
                case 23:
                    /*  2751 */ name = "San Juan";
                    /*  2752 */ break;
                case 24:
                    /*  2754 */ name = "San Pedro De Macoris";
                    /*  2755 */ break;
                case 25:
                    /*  2757 */ name = "Santiago";
                    /*  2758 */ break;
                case 26:
                    /*  2760 */ name = "Santiago Rodriguez";
                    /*  2761 */ break;
                case 27:
                    /*  2763 */ name = "Valverde";
                    /*  2764 */ break;
                case 28:
                    /*  2766 */ name = "El Seibo";
                    /*  2767 */ break;
                case 29:
                    /*  2769 */ name = "Hato Mayor";
                    /*  2770 */ break;
                case 30:
                    /*  2772 */ name = "La Vega";
                    /*  2773 */ break;
                case 31:
                    /*  2775 */ name = "Monsenor Nouel";
                    /*  2776 */ break;
                case 32:
                    /*  2778 */ name = "Monte Plata";
                    /*  2779 */ break;
                case 33:
                    /*  2781 */ name = "San Cristobal";
                    /*  2782 */ break;
                case 34:
                    /*  2784 */ name = "Distrito Nacional";
                    /*  2785 */ break;
                case 35:
                    /*  2787 */ name = "Peravia";
                    /*  2788 */ break;
                case 36:
                    /*  2790 */ name = "San Jose de Ocoa";
                    /*  2791 */ break;
                case 37:
                    /*  2793 */ name = "Santo Domingo";
                case 7:
                case 13:
                case 22:
            }
        }
        /*  2797 */ if (country_code.equals("DZ") == true) /*  2798 */ {
            switch (region_code2) {
                case 1:
                    /*  2800 */ name = "Alger";
                    /*  2801 */ break;
                case 3:
                    /*  2803 */ name = "Batna";
                    /*  2804 */ break;
                case 4:
                    /*  2806 */ name = "Constantine";
                    /*  2807 */ break;
                case 6:
                    /*  2809 */ name = "Medea";
                    /*  2810 */ break;
                case 7:
                    /*  2812 */ name = "Mostaganem";
                    /*  2813 */ break;
                case 9:
                    /*  2815 */ name = "Oran";
                    /*  2816 */ break;
                case 10:
                    /*  2818 */ name = "Saida";
                    /*  2819 */ break;
                case 12:
                    /*  2821 */ name = "Setif";
                    /*  2822 */ break;
                case 13:
                    /*  2824 */ name = "Tiaret";
                    /*  2825 */ break;
                case 14:
                    /*  2827 */ name = "Tizi Ouzou";
                    /*  2828 */ break;
                case 15:
                    /*  2830 */ name = "Tlemcen";
                    /*  2831 */ break;
                case 18:
                    /*  2833 */ name = "Bejaia";
                    /*  2834 */ break;
                case 19:
                    /*  2836 */ name = "Biskra";
                    /*  2837 */ break;
                case 20:
                    /*  2839 */ name = "Blida";
                    /*  2840 */ break;
                case 21:
                    /*  2842 */ name = "Bouira";
                    /*  2843 */ break;
                case 22:
                    /*  2845 */ name = "Djelfa";
                    /*  2846 */ break;
                case 23:
                    /*  2848 */ name = "Guelma";
                    /*  2849 */ break;
                case 24:
                    /*  2851 */ name = "Jijel";
                    /*  2852 */ break;
                case 25:
                    /*  2854 */ name = "Laghouat";
                    /*  2855 */ break;
                case 26:
                    /*  2857 */ name = "Mascara";
                    /*  2858 */ break;
                case 27:
                    /*  2860 */ name = "M'sila";
                    /*  2861 */ break;
                case 29:
                    /*  2863 */ name = "Oum el Bouaghi";
                    /*  2864 */ break;
                case 30:
                    /*  2866 */ name = "Sidi Bel Abbes";
                    /*  2867 */ break;
                case 31:
                    /*  2869 */ name = "Skikda";
                    /*  2870 */ break;
                case 33:
                    /*  2872 */ name = "Tebessa";
                    /*  2873 */ break;
                case 34:
                    /*  2875 */ name = "Adrar";
                    /*  2876 */ break;
                case 35:
                    /*  2878 */ name = "Ain Defla";
                    /*  2879 */ break;
                case 36:
                    /*  2881 */ name = "Ain Temouchent";
                    /*  2882 */ break;
                case 37:
                    /*  2884 */ name = "Annaba";
                    /*  2885 */ break;
                case 38:
                    /*  2887 */ name = "Bechar";
                    /*  2888 */ break;
                case 39:
                    /*  2890 */ name = "Bordj Bou Arreridj";
                    /*  2891 */ break;
                case 40:
                    /*  2893 */ name = "Boumerdes";
                    /*  2894 */ break;
                case 41:
                    /*  2896 */ name = "Chlef";
                    /*  2897 */ break;
                case 42:
                    /*  2899 */ name = "El Bayadh";
                    /*  2900 */ break;
                case 43:
                    /*  2902 */ name = "El Oued";
                    /*  2903 */ break;
                case 44:
                    /*  2905 */ name = "El Tarf";
                    /*  2906 */ break;
                case 45:
                    /*  2908 */ name = "Ghardaia";
                    /*  2909 */ break;
                case 46:
                    /*  2911 */ name = "Illizi";
                    /*  2912 */ break;
                case 47:
                    /*  2914 */ name = "Khenchela";
                    /*  2915 */ break;
                case 48:
                    /*  2917 */ name = "Mila";
                    /*  2918 */ break;
                case 49:
                    /*  2920 */ name = "Naama";
                    /*  2921 */ break;
                case 50:
                    /*  2923 */ name = "Ouargla";
                    /*  2924 */ break;
                case 51:
                    /*  2926 */ name = "Relizane";
                    /*  2927 */ break;
                case 52:
                    /*  2929 */ name = "Souk Ahras";
                    /*  2930 */ break;
                case 53:
                    /*  2932 */ name = "Tamanghasset";
                    /*  2933 */ break;
                case 54:
                    /*  2935 */ name = "Tindouf";
                    /*  2936 */ break;
                case 55:
                    /*  2938 */ name = "Tipaza";
                    /*  2939 */ break;
                case 56:
                    /*  2941 */ name = "Tissemsilt";
                case 2:
                case 5:
                case 8:
                case 11:
                case 16:
                case 17:
                case 28:
                /*  2945 */ case 32:
            }
        }
        if (country_code.equals("EC") == true) /*  2946 */ {
            switch (region_code2) {
                case 1:
                    /*  2948 */ name = "Galapagos";
                    /*  2949 */ break;
                case 2:
                    /*  2951 */ name = "Azuay";
                    /*  2952 */ break;
                case 3:
                    /*  2954 */ name = "Bolivar";
                    /*  2955 */ break;
                case 4:
                    /*  2957 */ name = "Canar";
                    /*  2958 */ break;
                case 5:
                    /*  2960 */ name = "Carchi";
                    /*  2961 */ break;
                case 6:
                    /*  2963 */ name = "Chimborazo";
                    /*  2964 */ break;
                case 7:
                    /*  2966 */ name = "Cotopaxi";
                    /*  2967 */ break;
                case 8:
                    /*  2969 */ name = "El Oro";
                    /*  2970 */ break;
                case 9:
                    /*  2972 */ name = "Esmeraldas";
                    /*  2973 */ break;
                case 10:
                    /*  2975 */ name = "Guayas";
                    /*  2976 */ break;
                case 11:
                    /*  2978 */ name = "Imbabura";
                    /*  2979 */ break;
                case 12:
                    /*  2981 */ name = "Loja";
                    /*  2982 */ break;
                case 13:
                    /*  2984 */ name = "Los Rios";
                    /*  2985 */ break;
                case 14:
                    /*  2987 */ name = "Manabi";
                    /*  2988 */ break;
                case 15:
                    /*  2990 */ name = "Morona-Santiago";
                    /*  2991 */ break;
                case 17:
                    /*  2993 */ name = "Pastaza";
                    /*  2994 */ break;
                case 18:
                    /*  2996 */ name = "Pichincha";
                    /*  2997 */ break;
                case 19:
                    /*  2999 */ name = "Tungurahua";
                    /*  3000 */ break;
                case 20:
                    /*  3002 */ name = "Zamora-Chinchipe";
                    /*  3003 */ break;
                case 22:
                    /*  3005 */ name = "Sucumbios";
                    /*  3006 */ break;
                case 23:
                    /*  3008 */ name = "Napo";
                    /*  3009 */ break;
                case 24:
                    /*  3011 */ name = "Orellana";
                case 16:
                case 21:
            }
        }
        /*  3015 */ if (country_code.equals("EE") == true) {
            /*  3016 */ switch (region_code2) {
                case 1:
                    /*  3018 */ name = "Harjumaa";
                    /*  3019 */ break;
                case 2:
                    /*  3021 */ name = "Hiiumaa";
                    /*  3022 */ break;
                case 3:
                    /*  3024 */ name = "Ida-Virumaa";
                    /*  3025 */ break;
                case 4:
                    /*  3027 */ name = "Jarvamaa";
                    /*  3028 */ break;
                case 5:
                    /*  3030 */ name = "Jogevamaa";
                    /*  3031 */ break;
                case 6:
                    /*  3033 */ name = "Kohtla-Jarve";
                    /*  3034 */ break;
                case 7:
                    /*  3036 */ name = "Laanemaa";
                    /*  3037 */ break;
                case 8:
                    /*  3039 */ name = "Laane-Virumaa";
                    /*  3040 */ break;
                case 9:
                    /*  3042 */ name = "Narva";
                    /*  3043 */ break;
                case 10:
                    /*  3045 */ name = "Parnu";
                    /*  3046 */ break;
                case 11:
                    /*  3048 */ name = "Parnumaa";
                    /*  3049 */ break;
                case 12:
                    /*  3051 */ name = "Polvamaa";
                    /*  3052 */ break;
                case 13:
                    /*  3054 */ name = "Raplamaa";
                    /*  3055 */ break;
                case 14:
                    /*  3057 */ name = "Saaremaa";
                    /*  3058 */ break;
                case 15:
                    /*  3060 */ name = "Sillamae";
                    /*  3061 */ break;
                case 16:
                    /*  3063 */ name = "Tallinn";
                    /*  3064 */ break;
                case 17:
                    /*  3066 */ name = "Tartu";
                    /*  3067 */ break;
                case 18:
                    /*  3069 */ name = "Tartumaa";
                    /*  3070 */ break;
                case 19:
                    /*  3072 */ name = "Valgamaa";
                    /*  3073 */ break;
                case 20:
                    /*  3075 */ name = "Viljandimaa";
                    /*  3076 */ break;
                case 21:
                    /*  3078 */ name = "Vorumaa";
            }
        }

        /*  3082 */ if (country_code.equals("EG") == true) {
            /*  3083 */ switch (region_code2) {
                case 1:
                    /*  3085 */ name = "Ad Daqahliyah";
                    /*  3086 */ break;
                case 2:
                    /*  3088 */ name = "Al Bahr al Ahmar";
                    /*  3089 */ break;
                case 3:
                    /*  3091 */ name = "Al Buhayrah";
                    /*  3092 */ break;
                case 4:
                    /*  3094 */ name = "Al Fayyum";
                    /*  3095 */ break;
                case 5:
                    /*  3097 */ name = "Al Gharbiyah";
                    /*  3098 */ break;
                case 6:
                    /*  3100 */ name = "Al Iskandariyah";
                    /*  3101 */ break;
                case 7:
                    /*  3103 */ name = "Al Isma'iliyah";
                    /*  3104 */ break;
                case 8:
                    /*  3106 */ name = "Al Jizah";
                    /*  3107 */ break;
                case 9:
                    /*  3109 */ name = "Al Minufiyah";
                    /*  3110 */ break;
                case 10:
                    /*  3112 */ name = "Al Minya";
                    /*  3113 */ break;
                case 11:
                    /*  3115 */ name = "Al Qahirah";
                    /*  3116 */ break;
                case 12:
                    /*  3118 */ name = "Al Qalyubiyah";
                    /*  3119 */ break;
                case 13:
                    /*  3121 */ name = "Al Wadi al Jadid";
                    /*  3122 */ break;
                case 14:
                    /*  3124 */ name = "Ash Sharqiyah";
                    /*  3125 */ break;
                case 15:
                    /*  3127 */ name = "As Suways";
                    /*  3128 */ break;
                case 16:
                    /*  3130 */ name = "Aswan";
                    /*  3131 */ break;
                case 17:
                    /*  3133 */ name = "Asyut";
                    /*  3134 */ break;
                case 18:
                    /*  3136 */ name = "Bani Suwayf";
                    /*  3137 */ break;
                case 19:
                    /*  3139 */ name = "Bur Sa'id";
                    /*  3140 */ break;
                case 20:
                    /*  3142 */ name = "Dumyat";
                    /*  3143 */ break;
                case 21:
                    /*  3145 */ name = "Kafr ash Shaykh";
                    /*  3146 */ break;
                case 22:
                    /*  3148 */ name = "Matruh";
                    /*  3149 */ break;
                case 23:
                    /*  3151 */ name = "Qina";
                    /*  3152 */ break;
                case 24:
                    /*  3154 */ name = "Suhaj";
                    /*  3155 */ break;
                case 26:
                    /*  3157 */ name = "Janub Sina'";
                    /*  3158 */ break;
                case 27:
                    /*  3160 */ name = "Shamal Sina'";
                case 25:
            }
        }
        /*  3164 */ if (country_code.equals("ER") == true) {
            /*  3165 */ switch (region_code2) {
                case 1:
                    /*  3167 */ name = "Anseba";
                    /*  3168 */ break;
                case 2:
                    /*  3170 */ name = "Debub";
                    /*  3171 */ break;
                case 3:
                    /*  3173 */ name = "Debubawi K'eyih Bahri";
                    /*  3174 */ break;
                case 4:
                    /*  3176 */ name = "Gash Barka";
                    /*  3177 */ break;
                case 5:
                    /*  3179 */ name = "Ma'akel";
                    /*  3180 */ break;
                case 6:
                    /*  3182 */ name = "Semenawi K'eyih Bahri";
            }
        }

        /*  3186 */ if (country_code.equals("ES") == true) /*  3187 */ {
            switch (region_code2) {
                case 7:
                    /*  3189 */ name = "Islas Baleares";
                    /*  3190 */ break;
                case 27:
                    /*  3192 */ name = "La Rioja";
                    /*  3193 */ break;
                case 29:
                    /*  3195 */ name = "Madrid";
                    /*  3196 */ break;
                case 31:
                    /*  3198 */ name = "Murcia";
                    /*  3199 */ break;
                case 32:
                    /*  3201 */ name = "Navarra";
                    /*  3202 */ break;
                case 34:
                    /*  3204 */ name = "Asturias";
                    /*  3205 */ break;
                case 39:
                    /*  3207 */ name = "Cantabria";
                    /*  3208 */ break;
                case 51:
                    /*  3210 */ name = "Andalucia";
                    /*  3211 */ break;
                case 52:
                    /*  3213 */ name = "Aragon";
                    /*  3214 */ break;
                case 53:
                    /*  3216 */ name = "Canarias";
                    /*  3217 */ break;
                case 54:
                    /*  3219 */ name = "Castilla-La Mancha";
                    /*  3220 */ break;
                case 55:
                    /*  3222 */ name = "Castilla y Leon";
                    /*  3223 */ break;
                case 56:
                    /*  3225 */ name = "Catalonia";
                    /*  3226 */ break;
                case 57:
                    /*  3228 */ name = "Extremadura";
                    /*  3229 */ break;
                case 58:
                    /*  3231 */ name = "Galicia";
                    /*  3232 */ break;
                case 59:
                    /*  3234 */ name = "Pais Vasco";
                    /*  3235 */ break;
                case 60:
                    /*  3237 */ name = "Comunidad Valenciana";
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 28:
                case 30:
                case 33:
                case 35:
                case 36:
                case 37:
                case 38:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                /*  3241 */ case 50:
            }
        }
        if (country_code.equals("ET") == true) {
            /*  3242 */ switch (region_code2) {
                case 44:
                    /*  3244 */ name = "Adis Abeba";
                    /*  3245 */ break;
                case 45:
                    /*  3247 */ name = "Afar";
                    /*  3248 */ break;
                case 46:
                    /*  3250 */ name = "Amara";
                    /*  3251 */ break;
                case 47:
                    /*  3253 */ name = "Binshangul Gumuz";
                    /*  3254 */ break;
                case 48:
                    /*  3256 */ name = "Dire Dawa";
                    /*  3257 */ break;
                case 49:
                    /*  3259 */ name = "Gambela Hizboch";
                    /*  3260 */ break;
                case 50:
                    /*  3262 */ name = "Hareri Hizb";
                    /*  3263 */ break;
                case 51:
                    /*  3265 */ name = "Oromiya";
                    /*  3266 */ break;
                case 52:
                    /*  3268 */ name = "Sumale";
                    /*  3269 */ break;
                case 53:
                    /*  3271 */ name = "Tigray";
                    /*  3272 */ break;
                case 54:
                    /*  3274 */ name = "YeDebub Biheroch Bihereseboch na Hizboch";
            }
        }

        /*  3278 */ if (country_code.equals("FI") == true) /*  3279 */ {
            switch (region_code2) {
                case 1:
                    /*  3281 */ name = "Aland";
                    /*  3282 */ break;
                case 6:
                    /*  3284 */ name = "Lapland";
                    /*  3285 */ break;
                case 8:
                    /*  3287 */ name = "Oulu";
                    /*  3288 */ break;
                case 13:
                    /*  3290 */ name = "Southern Finland";
                    /*  3291 */ break;
                case 14:
                    /*  3293 */ name = "Eastern Finland";
                    /*  3294 */ break;
                case 15:
                    /*  3296 */ name = "Western Finland";
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 9:
                case 10:
                case 11:
                /*  3300 */ case 12:
            }
        }
        if (country_code.equals("FJ") == true) {
            /*  3301 */ switch (region_code2) {
                case 1:
                    /*  3303 */ name = "Central";
                    /*  3304 */ break;
                case 2:
                    /*  3306 */ name = "Eastern";
                    /*  3307 */ break;
                case 3:
                    /*  3309 */ name = "Northern";
                    /*  3310 */ break;
                case 4:
                    /*  3312 */ name = "Rotuma";
                    /*  3313 */ break;
                case 5:
                    /*  3315 */ name = "Western";
            }
        }

        /*  3319 */ if (country_code.equals("FM") == true) {
            /*  3320 */ switch (region_code2) {
                case 1:
                    /*  3322 */ name = "Kosrae";
                    /*  3323 */ break;
                case 2:
                    /*  3325 */ name = "Pohnpei";
                    /*  3326 */ break;
                case 3:
                    /*  3328 */ name = "Chuuk";
                    /*  3329 */ break;
                case 4:
                    /*  3331 */ name = "Yap";
            }
        }

        /*  3335 */ if (country_code.equals("FR") == true) {
            /*  3336 */ switch (region_code2) {
                case 97:
                    /*  3338 */ name = "Aquitaine";
                    /*  3339 */ break;
                case 98:
                    /*  3341 */ name = "Auvergne";
                    /*  3342 */ break;
                case 99:
                    /*  3344 */ name = "Basse-Normandie";
                    /*  3345 */ break;
                case 832:
                    /*  3347 */ name = "Bourgogne";
                    /*  3348 */ break;
                case 833:
                    /*  3350 */ name = "Bretagne";
                    /*  3351 */ break;
                case 834:
                    /*  3353 */ name = "Centre";
                    /*  3354 */ break;
                case 835:
                    /*  3356 */ name = "Champagne-Ardenne";
                    /*  3357 */ break;
                case 836:
                    /*  3359 */ name = "Corse";
                    /*  3360 */ break;
                case 837:
                    /*  3362 */ name = "Franche-Comte";
                    /*  3363 */ break;
                case 838:
                    /*  3365 */ name = "Haute-Normandie";
                    /*  3366 */ break;
                case 839:
                    /*  3368 */ name = "Ile-de-France";
                    /*  3369 */ break;
                case 840:
                    /*  3371 */ name = "Languedoc-Roussillon";
                    /*  3372 */ break;
                case 875:
                    /*  3374 */ name = "Limousin";
                    /*  3375 */ break;
                case 876:
                    /*  3377 */ name = "Lorraine";
                    /*  3378 */ break;
                case 877:
                    /*  3380 */ name = "Midi-Pyrenees";
                    /*  3381 */ break;
                case 878:
                    /*  3383 */ name = "Nord-Pas-de-Calais";
                    /*  3384 */ break;
                case 879:
                    /*  3386 */ name = "Pays de la Loire";
                    /*  3387 */ break;
                case 880:
                    /*  3389 */ name = "Picardie";
                    /*  3390 */ break;
                case 881:
                    /*  3392 */ name = "Poitou-Charentes";
                    /*  3393 */ break;
                case 882:
                    /*  3395 */ name = "Provence-Alpes-Cote d'Azur";
                    /*  3396 */ break;
                case 883:
                    /*  3398 */ name = "Rhone-Alpes";
                    /*  3399 */ break;
                case 918:
                    /*  3401 */ name = "Alsace";
            }
        }

        /*  3405 */ if (country_code.equals("GA") == true) {
            /*  3406 */ switch (region_code2) {
                case 1:
                    /*  3408 */ name = "Estuaire";
                    /*  3409 */ break;
                case 2:
                    /*  3411 */ name = "Haut-Ogooue";
                    /*  3412 */ break;
                case 3:
                    /*  3414 */ name = "Moyen-Ogooue";
                    /*  3415 */ break;
                case 4:
                    /*  3417 */ name = "Ngounie";
                    /*  3418 */ break;
                case 5:
                    /*  3420 */ name = "Nyanga";
                    /*  3421 */ break;
                case 6:
                    /*  3423 */ name = "Ogooue-Ivindo";
                    /*  3424 */ break;
                case 7:
                    /*  3426 */ name = "Ogooue-Lolo";
                    /*  3427 */ break;
                case 8:
                    /*  3429 */ name = "Ogooue-Maritime";
                    /*  3430 */ break;
                case 9:
                    /*  3432 */ name = "Woleu-Ntem";
            }
        }

        /*  3436 */ if (country_code.equals("GB") == true) /*  3437 */ {
            switch (region_code2) {
                case 832:
                    /*  3439 */ name = "Barking and Dagenham";
                    /*  3440 */ break;
                case 833:
                    /*  3442 */ name = "Barnet";
                    /*  3443 */ break;
                case 834:
                    /*  3445 */ name = "Barnsley";
                    /*  3446 */ break;
                case 835:
                    /*  3448 */ name = "Bath and North East Somerset";
                    /*  3449 */ break;
                case 836:
                    /*  3451 */ name = "Bedfordshire";
                    /*  3452 */ break;
                case 837:
                    /*  3454 */ name = "Bexley";
                    /*  3455 */ break;
                case 838:
                    /*  3457 */ name = "Birmingham";
                    /*  3458 */ break;
                case 839:
                    /*  3460 */ name = "Blackburn with Darwen";
                    /*  3461 */ break;
                case 840:
                    /*  3463 */ name = "Blackpool";
                    /*  3464 */ break;
                case 875:
                    /*  3466 */ name = "Bolton";
                    /*  3467 */ break;
                case 876:
                    /*  3469 */ name = "Bournemouth";
                    /*  3470 */ break;
                case 877:
                    /*  3472 */ name = "Bracknell Forest";
                    /*  3473 */ break;
                case 878:
                    /*  3475 */ name = "Bradford";
                    /*  3476 */ break;
                case 879:
                    /*  3478 */ name = "Brent";
                    /*  3479 */ break;
                case 880:
                    /*  3481 */ name = "Brighton and Hove";
                    /*  3482 */ break;
                case 881:
                    /*  3484 */ name = "Bristol";
                    /*  3485 */ break;
                case 882:
                    /*  3487 */ name = "Bromley";
                    /*  3488 */ break;
                case 883:
                    /*  3490 */ name = "Buckinghamshire";
                    /*  3491 */ break;
                case 918:
                    /*  3493 */ name = "Bury";
                    /*  3494 */ break;
                case 919:
                    /*  3496 */ name = "Calderdale";
                    /*  3497 */ break;
                case 920:
                    /*  3499 */ name = "Cambridgeshire";
                    /*  3500 */ break;
                case 921:
                    /*  3502 */ name = "Camden";
                    /*  3503 */ break;
                case 922:
                    /*  3505 */ name = "Cheshire";
                    /*  3506 */ break;
                case 923:
                    /*  3508 */ name = "Cornwall";
                    /*  3509 */ break;
                case 924:
                    /*  3511 */ name = "Coventry";
                    /*  3512 */ break;
                case 925:
                    /*  3514 */ name = "Croydon";
                    /*  3515 */ break;
                case 926:
                    /*  3517 */ name = "Cumbria";
                    /*  3518 */ break;
                case 961:
                    /*  3520 */ name = "Darlington";
                    /*  3521 */ break;
                case 962:
                    /*  3523 */ name = "Derby";
                    /*  3524 */ break;
                case 963:
                    /*  3526 */ name = "Derbyshire";
                    /*  3527 */ break;
                case 964:
                    /*  3529 */ name = "Devon";
                    /*  3530 */ break;
                case 965:
                    /*  3532 */ name = "Doncaster";
                    /*  3533 */ break;
                case 966:
                    /*  3535 */ name = "Dorset";
                    /*  3536 */ break;
                case 967:
                    /*  3538 */ name = "Dudley";
                    /*  3539 */ break;
                case 968:
                    /*  3541 */ name = "Durham";
                    /*  3542 */ break;
                case 969:
                    /*  3544 */ name = "Ealing";
                    /*  3545 */ break;
                case 1004:
                    /*  3547 */ name = "East Riding of Yorkshire";
                    /*  3548 */ break;
                case 1005:
                    /*  3550 */ name = "East Sussex";
                    /*  3551 */ break;
                case 1006:
                    /*  3553 */ name = "Enfield";
                    /*  3554 */ break;
                case 1007:
                    /*  3556 */ name = "Essex";
                    /*  3557 */ break;
                case 1008:
                    /*  3559 */ name = "Gateshead";
                    /*  3560 */ break;
                case 1009:
                    /*  3562 */ name = "Gloucestershire";
                    /*  3563 */ break;
                case 1010:
                    /*  3565 */ name = "Greenwich";
                    /*  3566 */ break;
                case 1011:
                    /*  3568 */ name = "Hackney";
                    /*  3569 */ break;
                case 1012:
                    /*  3571 */ name = "Halton";
                    /*  3572 */ break;
                case 1047:
                    /*  3574 */ name = "Hammersmith and Fulham";
                    /*  3575 */ break;
                case 1048:
                    /*  3577 */ name = "Hampshire";
                    /*  3578 */ break;
                case 1049:
                    /*  3580 */ name = "Haringey";
                    /*  3581 */ break;
                case 1050:
                    /*  3583 */ name = "Harrow";
                    /*  3584 */ break;
                case 1051:
                    /*  3586 */ name = "Hartlepool";
                    /*  3587 */ break;
                case 1052:
                    /*  3589 */ name = "Havering";
                    /*  3590 */ break;
                case 1053:
                    /*  3592 */ name = "Herefordshire";
                    /*  3593 */ break;
                case 1054:
                    /*  3595 */ name = "Hertford";
                    /*  3596 */ break;
                case 1055:
                    /*  3598 */ name = "Hillingdon";
                    /*  3599 */ break;
                case 1090:
                    /*  3601 */ name = "Hounslow";
                    /*  3602 */ break;
                case 1091:
                    /*  3604 */ name = "Isle of Wight";
                    /*  3605 */ break;
                case 1092:
                    /*  3607 */ name = "Islington";
                    /*  3608 */ break;
                case 1093:
                    /*  3610 */ name = "Kensington and Chelsea";
                    /*  3611 */ break;
                case 1094:
                    /*  3613 */ name = "Kent";
                    /*  3614 */ break;
                case 1095:
                    /*  3616 */ name = "Kingston upon Hull";
                    /*  3617 */ break;
                case 1096:
                    /*  3619 */ name = "Kingston upon Thames";
                    /*  3620 */ break;
                case 1097:
                    /*  3622 */ name = "Kirklees";
                    /*  3623 */ break;
                case 1098:
                    /*  3625 */ name = "Knowsley";
                    /*  3626 */ break;
                case 1133:
                    /*  3628 */ name = "Lambeth";
                    /*  3629 */ break;
                case 1134:
                    /*  3631 */ name = "Lancashire";
                    /*  3632 */ break;
                case 1135:
                    /*  3634 */ name = "Leeds";
                    /*  3635 */ break;
                case 1136:
                    /*  3637 */ name = "Leicester";
                    /*  3638 */ break;
                case 1137:
                    /*  3640 */ name = "Leicestershire";
                    /*  3641 */ break;
                case 1138:
                    /*  3643 */ name = "Lewisham";
                    /*  3644 */ break;
                case 1139:
                    /*  3646 */ name = "Lincolnshire";
                    /*  3647 */ break;
                case 1140:
                    /*  3649 */ name = "Liverpool";
                    /*  3650 */ break;
                case 1141:
                    /*  3652 */ name = "London";
                    /*  3653 */ break;
                case 1176:
                    /*  3655 */ name = "Luton";
                    /*  3656 */ break;
                case 1177:
                    /*  3658 */ name = "Manchester";
                    /*  3659 */ break;
                case 1178:
                    /*  3661 */ name = "Medway";
                    /*  3662 */ break;
                case 1179:
                    /*  3664 */ name = "Merton";
                    /*  3665 */ break;
                case 1180:
                    /*  3667 */ name = "Middlesbrough";
                    /*  3668 */ break;
                case 1181:
                    /*  3670 */ name = "Milton Keynes";
                    /*  3671 */ break;
                case 1182:
                    /*  3673 */ name = "Newcastle upon Tyne";
                    /*  3674 */ break;
                case 1183:
                    /*  3676 */ name = "Newham";
                    /*  3677 */ break;
                case 1184:
                    /*  3679 */ name = "Norfolk";
                    /*  3680 */ break;
                case 1219:
                    /*  3682 */ name = "Northamptonshire";
                    /*  3683 */ break;
                case 1220:
                    /*  3685 */ name = "North East Lincolnshire";
                    /*  3686 */ break;
                case 1221:
                    /*  3688 */ name = "North Lincolnshire";
                    /*  3689 */ break;
                case 1222:
                    /*  3691 */ name = "North Somerset";
                    /*  3692 */ break;
                case 1223:
                    /*  3694 */ name = "North Tyneside";
                    /*  3695 */ break;
                case 1224:
                    /*  3697 */ name = "Northumberland";
                    /*  3698 */ break;
                case 1225:
                    /*  3700 */ name = "North Yorkshire";
                    /*  3701 */ break;
                case 1226:
                    /*  3703 */ name = "Nottingham";
                    /*  3704 */ break;
                case 1227:
                    /*  3706 */ name = "Nottinghamshire";
                    /*  3707 */ break;
                case 1262:
                    /*  3709 */ name = "Oldham";
                    /*  3710 */ break;
                case 1263:
                    /*  3712 */ name = "Oxfordshire";
                    /*  3713 */ break;
                case 1264:
                    /*  3715 */ name = "Peterborough";
                    /*  3716 */ break;
                case 1265:
                    /*  3718 */ name = "Plymouth";
                    /*  3719 */ break;
                case 1266:
                    /*  3721 */ name = "Poole";
                    /*  3722 */ break;
                case 1267:
                    /*  3724 */ name = "Portsmouth";
                    /*  3725 */ break;
                case 1268:
                    /*  3727 */ name = "Reading";
                    /*  3728 */ break;
                case 1269:
                    /*  3730 */ name = "Redbridge";
                    /*  3731 */ break;
                case 1270:
                    /*  3733 */ name = "Redcar and Cleveland";
                    /*  3734 */ break;
                case 1305:
                    /*  3736 */ name = "Richmond upon Thames";
                    /*  3737 */ break;
                case 1306:
                    /*  3739 */ name = "Rochdale";
                    /*  3740 */ break;
                case 1307:
                    /*  3742 */ name = "Rotherham";
                    /*  3743 */ break;
                case 1308:
                    /*  3745 */ name = "Rutland";
                    /*  3746 */ break;
                case 1309:
                    /*  3748 */ name = "Salford";
                    /*  3749 */ break;
                case 1310:
                    /*  3751 */ name = "Shropshire";
                    /*  3752 */ break;
                case 1311:
                    /*  3754 */ name = "Sandwell";
                    /*  3755 */ break;
                case 1312:
                    /*  3757 */ name = "Sefton";
                    /*  3758 */ break;
                case 1313:
                    /*  3760 */ name = "Sheffield";
                    /*  3761 */ break;
                case 1348:
                    /*  3763 */ name = "Slough";
                    /*  3764 */ break;
                case 1349:
                    /*  3766 */ name = "Solihull";
                    /*  3767 */ break;
                case 1350:
                    /*  3769 */ name = "Somerset";
                    /*  3770 */ break;
                case 1351:
                    /*  3772 */ name = "Southampton";
                    /*  3773 */ break;
                case 1352:
                    /*  3775 */ name = "Southend-on-Sea";
                    /*  3776 */ break;
                case 1353:
                    /*  3778 */ name = "South Gloucestershire";
                    /*  3779 */ break;
                case 1354:
                    /*  3781 */ name = "South Tyneside";
                    /*  3782 */ break;
                case 1355:
                    /*  3784 */ name = "Southwark";
                    /*  3785 */ break;
                case 1356:
                    /*  3787 */ name = "Staffordshire";
                    /*  3788 */ break;
                case 1391:
                    /*  3790 */ name = "St. Helens";
                    /*  3791 */ break;
                case 1392:
                    /*  3793 */ name = "Stockport";
                    /*  3794 */ break;
                case 1393:
                    /*  3796 */ name = "Stockton-on-Tees";
                    /*  3797 */ break;
                case 1394:
                    /*  3799 */ name = "Stoke-on-Trent";
                    /*  3800 */ break;
                case 1395:
                    /*  3802 */ name = "Suffolk";
                    /*  3803 */ break;
                case 1396:
                    /*  3805 */ name = "Sunderland";
                    /*  3806 */ break;
                case 1397:
                    /*  3808 */ name = "Surrey";
                    /*  3809 */ break;
                case 1398:
                    /*  3811 */ name = "Sutton";
                    /*  3812 */ break;
                case 1399:
                    /*  3814 */ name = "Swindon";
                    /*  3815 */ break;
                case 1434:
                    /*  3817 */ name = "Tameside";
                    /*  3818 */ break;
                case 1435:
                    /*  3820 */ name = "Telford and Wrekin";
                    /*  3821 */ break;
                case 1436:
                    /*  3823 */ name = "Thurrock";
                    /*  3824 */ break;
                case 1437:
                    /*  3826 */ name = "Torbay";
                    /*  3827 */ break;
                case 1438:
                    /*  3829 */ name = "Tower Hamlets";
                    /*  3830 */ break;
                case 1439:
                    /*  3832 */ name = "Trafford";
                    /*  3833 */ break;
                case 1440:
                    /*  3835 */ name = "Wakefield";
                    /*  3836 */ break;
                case 1441:
                    /*  3838 */ name = "Walsall";
                    /*  3839 */ break;
                case 1442:
                    /*  3841 */ name = "Waltham Forest";
                    /*  3842 */ break;
                case 1477:
                    /*  3844 */ name = "Wandsworth";
                    /*  3845 */ break;
                case 1478:
                    /*  3847 */ name = "Warrington";
                    /*  3848 */ break;
                case 1479:
                    /*  3850 */ name = "Warwickshire";
                    /*  3851 */ break;
                case 1480:
                    /*  3853 */ name = "West Berkshire";
                    /*  3854 */ break;
                case 1481:
                    /*  3856 */ name = "Westminster";
                    /*  3857 */ break;
                case 1482:
                    /*  3859 */ name = "West Sussex";
                    /*  3860 */ break;
                case 1483:
                    /*  3862 */ name = "Wigan";
                    /*  3863 */ break;
                case 1484:
                    /*  3865 */ name = "Wiltshire";
                    /*  3866 */ break;
                case 1485:
                    /*  3868 */ name = "Windsor and Maidenhead";
                    /*  3869 */ break;
                case 1520:
                    /*  3871 */ name = "Wirral";
                    /*  3872 */ break;
                case 1521:
                    /*  3874 */ name = "Wokingham";
                    /*  3875 */ break;
                case 1522:
                    /*  3877 */ name = "Wolverhampton";
                    /*  3878 */ break;
                case 1523:
                    /*  3880 */ name = "Worcestershire";
                    /*  3881 */ break;
                case 1524:
                    /*  3883 */ name = "York";
                    /*  3884 */ break;
                case 1525:
                    /*  3886 */ name = "Antrim";
                    /*  3887 */ break;
                case 1526:
                    /*  3889 */ name = "Ards";
                    /*  3890 */ break;
                case 1527:
                    /*  3892 */ name = "Armagh";
                    /*  3893 */ break;
                case 1528:
                    /*  3895 */ name = "Ballymena";
                    /*  3896 */ break;
                case 1563:
                    /*  3898 */ name = "Ballymoney";
                    /*  3899 */ break;
                case 1564:
                    /*  3901 */ name = "Banbridge";
                    /*  3902 */ break;
                case 1565:
                    /*  3904 */ name = "Belfast";
                    /*  3905 */ break;
                case 1566:
                    /*  3907 */ name = "Carrickfergus";
                    /*  3908 */ break;
                case 1567:
                    /*  3910 */ name = "Castlereagh";
                    /*  3911 */ break;
                case 1568:
                    /*  3913 */ name = "Coleraine";
                    /*  3914 */ break;
                case 1569:
                    /*  3916 */ name = "Cookstown";
                    /*  3917 */ break;
                case 1570:
                    /*  3919 */ name = "Craigavon";
                    /*  3920 */ break;
                case 1571:
                    /*  3922 */ name = "Down";
                    /*  3923 */ break;
                case 1606:
                    /*  3925 */ name = "Dungannon";
                    /*  3926 */ break;
                case 1607:
                    /*  3928 */ name = "Fermanagh";
                    /*  3929 */ break;
                case 1608:
                    /*  3931 */ name = "Larne";
                    /*  3932 */ break;
                case 1609:
                    /*  3934 */ name = "Limavady";
                    /*  3935 */ break;
                case 1610:
                    /*  3937 */ name = "Lisburn";
                    /*  3938 */ break;
                case 1611:
                    /*  3940 */ name = "Derry";
                    /*  3941 */ break;
                case 1612:
                    /*  3943 */ name = "Magherafelt";
                    /*  3944 */ break;
                case 1613:
                    /*  3946 */ name = "Moyle";
                    /*  3947 */ break;
                case 1614:
                    /*  3949 */ name = "Newry and Mourne";
                    /*  3950 */ break;
                case 1649:
                    /*  3952 */ name = "Newtownabbey";
                    /*  3953 */ break;
                case 1650:
                    /*  3955 */ name = "North Down";
                    /*  3956 */ break;
                case 1651:
                    /*  3958 */ name = "Omagh";
                    /*  3959 */ break;
                case 1652:
                    /*  3961 */ name = "Strabane";
                    /*  3962 */ break;
                case 1653:
                    /*  3964 */ name = "Aberdeen City";
                    /*  3965 */ break;
                case 1654:
                    /*  3967 */ name = "Aberdeenshire";
                    /*  3968 */ break;
                case 1655:
                    /*  3970 */ name = "Angus";
                    /*  3971 */ break;
                case 1656:
                    /*  3973 */ name = "Argyll and Bute";
                    /*  3974 */ break;
                case 1657:
                    /*  3976 */ name = "Scottish Borders";
                    /*  3977 */ break;
                case 1692:
                    /*  3979 */ name = "Clackmannanshire";
                    /*  3980 */ break;
                case 1693:
                    /*  3982 */ name = "Dumfries and Galloway";
                    /*  3983 */ break;
                case 1694:
                    /*  3985 */ name = "Dundee City";
                    /*  3986 */ break;
                case 1695:
                    /*  3988 */ name = "East Ayrshire";
                    /*  3989 */ break;
                case 1696:
                    /*  3991 */ name = "East Dunbartonshire";
                    /*  3992 */ break;
                case 1697:
                    /*  3994 */ name = "East Lothian";
                    /*  3995 */ break;
                case 1698:
                    /*  3997 */ name = "East Renfrewshire";
                    /*  3998 */ break;
                case 1699:
                    /*  4000 */ name = "Edinburgh";
                    /*  4001 */ break;
                case 1700:
                    /*  4003 */ name = "Falkirk";
                    /*  4004 */ break;
                case 1735:
                    /*  4006 */ name = "Fife";
                    /*  4007 */ break;
                case 1736:
                    /*  4009 */ name = "Glasgow City";
                    /*  4010 */ break;
                case 1737:
                    /*  4012 */ name = "Highland";
                    /*  4013 */ break;
                case 1738:
                    /*  4015 */ name = "Inverclyde";
                    /*  4016 */ break;
                case 1739:
                    /*  4018 */ name = "Midlothian";
                    /*  4019 */ break;
                case 1740:
                    /*  4021 */ name = "Moray";
                    /*  4022 */ break;
                case 1741:
                    /*  4024 */ name = "North Ayrshire";
                    /*  4025 */ break;
                case 1742:
                    /*  4027 */ name = "North Lanarkshire";
                    /*  4028 */ break;
                case 1743:
                    /*  4030 */ name = "Orkney";
                    /*  4031 */ break;
                case 1778:
                    /*  4033 */ name = "Perth and Kinross";
                    /*  4034 */ break;
                case 1779:
                    /*  4036 */ name = "Renfrewshire";
                    /*  4037 */ break;
                case 1780:
                    /*  4039 */ name = "Shetland Islands";
                    /*  4040 */ break;
                case 1781:
                    /*  4042 */ name = "South Ayrshire";
                    /*  4043 */ break;
                case 1782:
                    /*  4045 */ name = "South Lanarkshire";
                    /*  4046 */ break;
                case 1783:
                    /*  4048 */ name = "Stirling";
                    /*  4049 */ break;
                case 1784:
                    /*  4051 */ name = "West Dunbartonshire";
                    /*  4052 */ break;
                case 1785:
                    /*  4054 */ name = "Eilean Siar";
                    /*  4055 */ break;
                case 1786:
                    /*  4057 */ name = "West Lothian";
                    /*  4058 */ break;
                case 1821:
                    /*  4060 */ name = "Isle of Anglesey";
                    /*  4061 */ break;
                case 1822:
                    /*  4063 */ name = "Blaenau Gwent";
                    /*  4064 */ break;
                case 1823:
                    /*  4066 */ name = "Bridgend";
                    /*  4067 */ break;
                case 1824:
                    /*  4069 */ name = "Caerphilly";
                    /*  4070 */ break;
                case 1825:
                    /*  4072 */ name = "Cardiff";
                    /*  4073 */ break;
                case 1826:
                    /*  4075 */ name = "Ceredigion";
                    /*  4076 */ break;
                case 1827:
                    /*  4078 */ name = "Carmarthenshire";
                    /*  4079 */ break;
                case 1828:
                    /*  4081 */ name = "Conwy";
                    /*  4082 */ break;
                case 1829:
                    /*  4084 */ name = "Denbighshire";
                    /*  4085 */ break;
                case 1864:
                    /*  4087 */ name = "Flintshire";
                    /*  4088 */ break;
                case 1865:
                    /*  4090 */ name = "Gwynedd";
                    /*  4091 */ break;
                case 1866:
                    /*  4093 */ name = "Merthyr Tydfil";
                    /*  4094 */ break;
                case 1867:
                    /*  4096 */ name = "Monmouthshire";
                    /*  4097 */ break;
                case 1868:
                    /*  4099 */ name = "Neath Port Talbot";
                    /*  4100 */ break;
                case 1869:
                    /*  4102 */ name = "Newport";
                    /*  4103 */ break;
                case 1870:
                    /*  4105 */ name = "Pembrokeshire";
                    /*  4106 */ break;
                case 1871:
                    /*  4108 */ name = "Powys";
                    /*  4109 */ break;
                case 1872:
                    /*  4111 */ name = "Rhondda Cynon Taff";
                    /*  4112 */ break;
                case 1907:
                    /*  4114 */ name = "Swansea";
                    /*  4115 */ break;
                case 1908:
                    /*  4117 */ name = "Torfaen";
                    /*  4118 */ break;
                case 1909:
                    /*  4120 */ name = "Vale of Glamorgan";
                    /*  4121 */ break;
                case 1910:
                    /*  4123 */ name = "Wrexham";
                case 841:
                case 842:
                case 843:
                case 844:
                case 845:
                case 846:
                case 847:
                case 848:
                case 849:
                case 850:
                case 851:
                case 852:
                case 853:
                case 854:
                case 855:
                case 856:
                case 857:
                case 858:
                case 859:
                case 860:
                case 861:
                case 862:
                case 863:
                case 864:
                case 865:
                case 866:
                case 867:
                case 868:
                case 869:
                case 870:
                case 871:
                case 872:
                case 873:
                case 874:
                case 884:
                case 885:
                case 886:
                case 887:
                case 888:
                case 889:
                case 890:
                case 891:
                case 892:
                case 893:
                case 894:
                case 895:
                case 896:
                case 897:
                case 898:
                case 899:
                case 900:
                case 901:
                case 902:
                case 903:
                case 904:
                case 905:
                case 906:
                case 907:
                case 908:
                case 909:
                case 910:
                case 911:
                case 912:
                case 913:
                case 914:
                case 915:
                case 916:
                case 917:
                case 927:
                case 928:
                case 929:
                case 930:
                case 931:
                case 932:
                case 933:
                case 934:
                case 935:
                case 936:
                case 937:
                case 938:
                case 939:
                case 940:
                case 941:
                case 942:
                case 943:
                case 944:
                case 945:
                case 946:
                case 947:
                case 948:
                case 949:
                case 950:
                case 951:
                case 952:
                case 953:
                case 954:
                case 955:
                case 956:
                case 957:
                case 958:
                case 959:
                case 960:
                case 970:
                case 971:
                case 972:
                case 973:
                case 974:
                case 975:
                case 976:
                case 977:
                case 978:
                case 979:
                case 980:
                case 981:
                case 982:
                case 983:
                case 984:
                case 985:
                case 986:
                case 987:
                case 988:
                case 989:
                case 990:
                case 991:
                case 992:
                case 993:
                case 994:
                case 995:
                case 996:
                case 997:
                case 998:
                case 999:
                case 1000:
                case 1001:
                case 1002:
                case 1003:
                case 1013:
                case 1014:
                case 1015:
                case 1016:
                case 1017:
                case 1018:
                case 1019:
                case 1020:
                case 1021:
                case 1022:
                case 1023:
                case 1024:
                case 1025:
                case 1026:
                case 1027:
                case 1028:
                case 1029:
                case 1030:
                case 1031:
                case 1032:
                case 1033:
                case 1034:
                case 1035:
                case 1036:
                case 1037:
                case 1038:
                case 1039:
                case 1040:
                case 1041:
                case 1042:
                case 1043:
                case 1044:
                case 1045:
                case 1046:
                case 1056:
                case 1057:
                case 1058:
                case 1059:
                case 1060:
                case 1061:
                case 1062:
                case 1063:
                case 1064:
                case 1065:
                case 1066:
                case 1067:
                case 1068:
                case 1069:
                case 1070:
                case 1071:
                case 1072:
                case 1073:
                case 1074:
                case 1075:
                case 1076:
                case 1077:
                case 1078:
                case 1079:
                case 1080:
                case 1081:
                case 1082:
                case 1083:
                case 1084:
                case 1085:
                case 1086:
                case 1087:
                case 1088:
                case 1089:
                case 1099:
                case 1100:
                case 1101:
                case 1102:
                case 1103:
                case 1104:
                case 1105:
                case 1106:
                case 1107:
                case 1108:
                case 1109:
                case 1110:
                case 1111:
                case 1112:
                case 1113:
                case 1114:
                case 1115:
                case 1116:
                case 1117:
                case 1118:
                case 1119:
                case 1120:
                case 1121:
                case 1122:
                case 1123:
                case 1124:
                case 1125:
                case 1126:
                case 1127:
                case 1128:
                case 1129:
                case 1130:
                case 1131:
                case 1132:
                case 1142:
                case 1143:
                case 1144:
                case 1145:
                case 1146:
                case 1147:
                case 1148:
                case 1149:
                case 1150:
                case 1151:
                case 1152:
                case 1153:
                case 1154:
                case 1155:
                case 1156:
                case 1157:
                case 1158:
                case 1159:
                case 1160:
                case 1161:
                case 1162:
                case 1163:
                case 1164:
                case 1165:
                case 1166:
                case 1167:
                case 1168:
                case 1169:
                case 1170:
                case 1171:
                case 1172:
                case 1173:
                case 1174:
                case 1175:
                case 1185:
                case 1186:
                case 1187:
                case 1188:
                case 1189:
                case 1190:
                case 1191:
                case 1192:
                case 1193:
                case 1194:
                case 1195:
                case 1196:
                case 1197:
                case 1198:
                case 1199:
                case 1200:
                case 1201:
                case 1202:
                case 1203:
                case 1204:
                case 1205:
                case 1206:
                case 1207:
                case 1208:
                case 1209:
                case 1210:
                case 1211:
                case 1212:
                case 1213:
                case 1214:
                case 1215:
                case 1216:
                case 1217:
                case 1218:
                case 1228:
                case 1229:
                case 1230:
                case 1231:
                case 1232:
                case 1233:
                case 1234:
                case 1235:
                case 1236:
                case 1237:
                case 1238:
                case 1239:
                case 1240:
                case 1241:
                case 1242:
                case 1243:
                case 1244:
                case 1245:
                case 1246:
                case 1247:
                case 1248:
                case 1249:
                case 1250:
                case 1251:
                case 1252:
                case 1253:
                case 1254:
                case 1255:
                case 1256:
                case 1257:
                case 1258:
                case 1259:
                case 1260:
                case 1261:
                case 1271:
                case 1272:
                case 1273:
                case 1274:
                case 1275:
                case 1276:
                case 1277:
                case 1278:
                case 1279:
                case 1280:
                case 1281:
                case 1282:
                case 1283:
                case 1284:
                case 1285:
                case 1286:
                case 1287:
                case 1288:
                case 1289:
                case 1290:
                case 1291:
                case 1292:
                case 1293:
                case 1294:
                case 1295:
                case 1296:
                case 1297:
                case 1298:
                case 1299:
                case 1300:
                case 1301:
                case 1302:
                case 1303:
                case 1304:
                case 1314:
                case 1315:
                case 1316:
                case 1317:
                case 1318:
                case 1319:
                case 1320:
                case 1321:
                case 1322:
                case 1323:
                case 1324:
                case 1325:
                case 1326:
                case 1327:
                case 1328:
                case 1329:
                case 1330:
                case 1331:
                case 1332:
                case 1333:
                case 1334:
                case 1335:
                case 1336:
                case 1337:
                case 1338:
                case 1339:
                case 1340:
                case 1341:
                case 1342:
                case 1343:
                case 1344:
                case 1345:
                case 1346:
                case 1347:
                case 1357:
                case 1358:
                case 1359:
                case 1360:
                case 1361:
                case 1362:
                case 1363:
                case 1364:
                case 1365:
                case 1366:
                case 1367:
                case 1368:
                case 1369:
                case 1370:
                case 1371:
                case 1372:
                case 1373:
                case 1374:
                case 1375:
                case 1376:
                case 1377:
                case 1378:
                case 1379:
                case 1380:
                case 1381:
                case 1382:
                case 1383:
                case 1384:
                case 1385:
                case 1386:
                case 1387:
                case 1388:
                case 1389:
                case 1390:
                case 1400:
                case 1401:
                case 1402:
                case 1403:
                case 1404:
                case 1405:
                case 1406:
                case 1407:
                case 1408:
                case 1409:
                case 1410:
                case 1411:
                case 1412:
                case 1413:
                case 1414:
                case 1415:
                case 1416:
                case 1417:
                case 1418:
                case 1419:
                case 1420:
                case 1421:
                case 1422:
                case 1423:
                case 1424:
                case 1425:
                case 1426:
                case 1427:
                case 1428:
                case 1429:
                case 1430:
                case 1431:
                case 1432:
                case 1433:
                case 1443:
                case 1444:
                case 1445:
                case 1446:
                case 1447:
                case 1448:
                case 1449:
                case 1450:
                case 1451:
                case 1452:
                case 1453:
                case 1454:
                case 1455:
                case 1456:
                case 1457:
                case 1458:
                case 1459:
                case 1460:
                case 1461:
                case 1462:
                case 1463:
                case 1464:
                case 1465:
                case 1466:
                case 1467:
                case 1468:
                case 1469:
                case 1470:
                case 1471:
                case 1472:
                case 1473:
                case 1474:
                case 1475:
                case 1476:
                case 1486:
                case 1487:
                case 1488:
                case 1489:
                case 1490:
                case 1491:
                case 1492:
                case 1493:
                case 1494:
                case 1495:
                case 1496:
                case 1497:
                case 1498:
                case 1499:
                case 1500:
                case 1501:
                case 1502:
                case 1503:
                case 1504:
                case 1505:
                case 1506:
                case 1507:
                case 1508:
                case 1509:
                case 1510:
                case 1511:
                case 1512:
                case 1513:
                case 1514:
                case 1515:
                case 1516:
                case 1517:
                case 1518:
                case 1519:
                case 1529:
                case 1530:
                case 1531:
                case 1532:
                case 1533:
                case 1534:
                case 1535:
                case 1536:
                case 1537:
                case 1538:
                case 1539:
                case 1540:
                case 1541:
                case 1542:
                case 1543:
                case 1544:
                case 1545:
                case 1546:
                case 1547:
                case 1548:
                case 1549:
                case 1550:
                case 1551:
                case 1552:
                case 1553:
                case 1554:
                case 1555:
                case 1556:
                case 1557:
                case 1558:
                case 1559:
                case 1560:
                case 1561:
                case 1562:
                case 1572:
                case 1573:
                case 1574:
                case 1575:
                case 1576:
                case 1577:
                case 1578:
                case 1579:
                case 1580:
                case 1581:
                case 1582:
                case 1583:
                case 1584:
                case 1585:
                case 1586:
                case 1587:
                case 1588:
                case 1589:
                case 1590:
                case 1591:
                case 1592:
                case 1593:
                case 1594:
                case 1595:
                case 1596:
                case 1597:
                case 1598:
                case 1599:
                case 1600:
                case 1601:
                case 1602:
                case 1603:
                case 1604:
                case 1605:
                case 1615:
                case 1616:
                case 1617:
                case 1618:
                case 1619:
                case 1620:
                case 1621:
                case 1622:
                case 1623:
                case 1624:
                case 1625:
                case 1626:
                case 1627:
                case 1628:
                case 1629:
                case 1630:
                case 1631:
                case 1632:
                case 1633:
                case 1634:
                case 1635:
                case 1636:
                case 1637:
                case 1638:
                case 1639:
                case 1640:
                case 1641:
                case 1642:
                case 1643:
                case 1644:
                case 1645:
                case 1646:
                case 1647:
                case 1648:
                case 1658:
                case 1659:
                case 1660:
                case 1661:
                case 1662:
                case 1663:
                case 1664:
                case 1665:
                case 1666:
                case 1667:
                case 1668:
                case 1669:
                case 1670:
                case 1671:
                case 1672:
                case 1673:
                case 1674:
                case 1675:
                case 1676:
                case 1677:
                case 1678:
                case 1679:
                case 1680:
                case 1681:
                case 1682:
                case 1683:
                case 1684:
                case 1685:
                case 1686:
                case 1687:
                case 1688:
                case 1689:
                case 1690:
                case 1691:
                case 1701:
                case 1702:
                case 1703:
                case 1704:
                case 1705:
                case 1706:
                case 1707:
                case 1708:
                case 1709:
                case 1710:
                case 1711:
                case 1712:
                case 1713:
                case 1714:
                case 1715:
                case 1716:
                case 1717:
                case 1718:
                case 1719:
                case 1720:
                case 1721:
                case 1722:
                case 1723:
                case 1724:
                case 1725:
                case 1726:
                case 1727:
                case 1728:
                case 1729:
                case 1730:
                case 1731:
                case 1732:
                case 1733:
                case 1734:
                case 1744:
                case 1745:
                case 1746:
                case 1747:
                case 1748:
                case 1749:
                case 1750:
                case 1751:
                case 1752:
                case 1753:
                case 1754:
                case 1755:
                case 1756:
                case 1757:
                case 1758:
                case 1759:
                case 1760:
                case 1761:
                case 1762:
                case 1763:
                case 1764:
                case 1765:
                case 1766:
                case 1767:
                case 1768:
                case 1769:
                case 1770:
                case 1771:
                case 1772:
                case 1773:
                case 1774:
                case 1775:
                case 1776:
                case 1777:
                case 1787:
                case 1788:
                case 1789:
                case 1790:
                case 1791:
                case 1792:
                case 1793:
                case 1794:
                case 1795:
                case 1796:
                case 1797:
                case 1798:
                case 1799:
                case 1800:
                case 1801:
                case 1802:
                case 1803:
                case 1804:
                case 1805:
                case 1806:
                case 1807:
                case 1808:
                case 1809:
                case 1810:
                case 1811:
                case 1812:
                case 1813:
                case 1814:
                case 1815:
                case 1816:
                case 1817:
                case 1818:
                case 1819:
                case 1820:
                case 1830:
                case 1831:
                case 1832:
                case 1833:
                case 1834:
                case 1835:
                case 1836:
                case 1837:
                case 1838:
                case 1839:
                case 1840:
                case 1841:
                case 1842:
                case 1843:
                case 1844:
                case 1845:
                case 1846:
                case 1847:
                case 1848:
                case 1849:
                case 1850:
                case 1851:
                case 1852:
                case 1853:
                case 1854:
                case 1855:
                case 1856:
                case 1857:
                case 1858:
                case 1859:
                case 1860:
                case 1861:
                case 1862:
                case 1863:
                case 1873:
                case 1874:
                case 1875:
                case 1876:
                case 1877:
                case 1878:
                case 1879:
                case 1880:
                case 1881:
                case 1882:
                case 1883:
                case 1884:
                case 1885:
                case 1886:
                case 1887:
                case 1888:
                case 1889:
                case 1890:
                case 1891:
                case 1892:
                case 1893:
                case 1894:
                case 1895:
                case 1896:
                case 1897:
                case 1898:
                case 1899:
                case 1900:
                case 1901:
                case 1902:
                case 1903:
                case 1904:
                case 1905:
                /*  4127 */ case 1906:
            }
        }
        if (country_code.equals("GD") == true) {
            /*  4128 */ switch (region_code2) {
                case 1:
                    /*  4130 */ name = "Saint Andrew";
                    /*  4131 */ break;
                case 2:
                    /*  4133 */ name = "Saint David";
                    /*  4134 */ break;
                case 3:
                    /*  4136 */ name = "Saint George";
                    /*  4137 */ break;
                case 4:
                    /*  4139 */ name = "Saint John";
                    /*  4140 */ break;
                case 5:
                    /*  4142 */ name = "Saint Mark";
                    /*  4143 */ break;
                case 6:
                    /*  4145 */ name = "Saint Patrick";
            }
        }

        /*  4149 */ if (country_code.equals("GE") == true) {
            /*  4150 */ switch (region_code2) {
                case 1:
                    /*  4152 */ name = "Abashis Raioni";
                    /*  4153 */ break;
                case 2:
                    /*  4155 */ name = "Abkhazia";
                    /*  4156 */ break;
                case 3:
                    /*  4158 */ name = "Adigenis Raioni";
                    /*  4159 */ break;
                case 4:
                    /*  4161 */ name = "Ajaria";
                    /*  4162 */ break;
                case 5:
                    /*  4164 */ name = "Akhalgoris Raioni";
                    /*  4165 */ break;
                case 6:
                    /*  4167 */ name = "Akhalk'alak'is Raioni";
                    /*  4168 */ break;
                case 7:
                    /*  4170 */ name = "Akhalts'ikhis Raioni";
                    /*  4171 */ break;
                case 8:
                    /*  4173 */ name = "Akhmetis Raioni";
                    /*  4174 */ break;
                case 9:
                    /*  4176 */ name = "Ambrolauris Raioni";
                    /*  4177 */ break;
                case 10:
                    /*  4179 */ name = "Aspindzis Raioni";
                    /*  4180 */ break;
                case 11:
                    /*  4182 */ name = "Baghdat'is Raioni";
                    /*  4183 */ break;
                case 12:
                    /*  4185 */ name = "Bolnisis Raioni";
                    /*  4186 */ break;
                case 13:
                    /*  4188 */ name = "Borjomis Raioni";
                    /*  4189 */ break;
                case 14:
                    /*  4191 */ name = "Chiat'ura";
                    /*  4192 */ break;
                case 15:
                    /*  4194 */ name = "Ch'khorotsqus Raioni";
                    /*  4195 */ break;
                case 16:
                    /*  4197 */ name = "Ch'okhatauris Raioni";
                    /*  4198 */ break;
                case 17:
                    /*  4200 */ name = "Dedop'listsqaros Raioni";
                    /*  4201 */ break;
                case 18:
                    /*  4203 */ name = "Dmanisis Raioni";
                    /*  4204 */ break;
                case 19:
                    /*  4206 */ name = "Dushet'is Raioni";
                    /*  4207 */ break;
                case 20:
                    /*  4209 */ name = "Gardabanis Raioni";
                    /*  4210 */ break;
                case 21:
                    /*  4212 */ name = "Gori";
                    /*  4213 */ break;
                case 22:
                    /*  4215 */ name = "Goris Raioni";
                    /*  4216 */ break;
                case 23:
                    /*  4218 */ name = "Gurjaanis Raioni";
                    /*  4219 */ break;
                case 24:
                    /*  4221 */ name = "Javis Raioni";
                    /*  4222 */ break;
                case 25:
                    /*  4224 */ name = "K'arelis Raioni";
                    /*  4225 */ break;
                case 26:
                    /*  4227 */ name = "Kaspis Raioni";
                    /*  4228 */ break;
                case 27:
                    /*  4230 */ name = "Kharagaulis Raioni";
                    /*  4231 */ break;
                case 28:
                    /*  4233 */ name = "Khashuris Raioni";
                    /*  4234 */ break;
                case 29:
                    /*  4236 */ name = "Khobis Raioni";
                    /*  4237 */ break;
                case 30:
                    /*  4239 */ name = "Khonis Raioni";
                    /*  4240 */ break;
                case 31:
                    /*  4242 */ name = "K'ut'aisi";
                    /*  4243 */ break;
                case 32:
                    /*  4245 */ name = "Lagodekhis Raioni";
                    /*  4246 */ break;
                case 33:
                    /*  4248 */ name = "Lanch'khut'is Raioni";
                    /*  4249 */ break;
                case 34:
                    /*  4251 */ name = "Lentekhis Raioni";
                    /*  4252 */ break;
                case 35:
                    /*  4254 */ name = "Marneulis Raioni";
                    /*  4255 */ break;
                case 36:
                    /*  4257 */ name = "Martvilis Raioni";
                    /*  4258 */ break;
                case 37:
                    /*  4260 */ name = "Mestiis Raioni";
                    /*  4261 */ break;
                case 38:
                    /*  4263 */ name = "Mts'khet'is Raioni";
                    /*  4264 */ break;
                case 39:
                    /*  4266 */ name = "Ninotsmindis Raioni";
                    /*  4267 */ break;
                case 40:
                    /*  4269 */ name = "Onis Raioni";
                    /*  4270 */ break;
                case 41:
                    /*  4272 */ name = "Ozurget'is Raioni";
                    /*  4273 */ break;
                case 42:
                    /*  4275 */ name = "P'ot'i";
                    /*  4276 */ break;
                case 43:
                    /*  4278 */ name = "Qazbegis Raioni";
                    /*  4279 */ break;
                case 44:
                    /*  4281 */ name = "Qvarlis Raioni";
                    /*  4282 */ break;
                case 45:
                    /*  4284 */ name = "Rust'avi";
                    /*  4285 */ break;
                case 46:
                    /*  4287 */ name = "Sach'kheris Raioni";
                    /*  4288 */ break;
                case 47:
                    /*  4290 */ name = "Sagarejos Raioni";
                    /*  4291 */ break;
                case 48:
                    /*  4293 */ name = "Samtrediis Raioni";
                    /*  4294 */ break;
                case 49:
                    /*  4296 */ name = "Senakis Raioni";
                    /*  4297 */ break;
                case 50:
                    /*  4299 */ name = "Sighnaghis Raioni";
                    /*  4300 */ break;
                case 51:
                    /*  4302 */ name = "T'bilisi";
                    /*  4303 */ break;
                case 52:
                    /*  4305 */ name = "T'elavis Raioni";
                    /*  4306 */ break;
                case 53:
                    /*  4308 */ name = "T'erjolis Raioni";
                    /*  4309 */ break;
                case 54:
                    /*  4311 */ name = "T'et'ritsqaros Raioni";
                    /*  4312 */ break;
                case 55:
                    /*  4314 */ name = "T'ianet'is Raioni";
                    /*  4315 */ break;
                case 56:
                    /*  4317 */ name = "Tqibuli";
                    /*  4318 */ break;
                case 57:
                    /*  4320 */ name = "Ts'ageris Raioni";
                    /*  4321 */ break;
                case 58:
                    /*  4323 */ name = "Tsalenjikhis Raioni";
                    /*  4324 */ break;
                case 59:
                    /*  4326 */ name = "Tsalkis Raioni";
                    /*  4327 */ break;
                case 60:
                    /*  4329 */ name = "Tsqaltubo";
                    /*  4330 */ break;
                case 61:
                    /*  4332 */ name = "Vanis Raioni";
                    /*  4333 */ break;
                case 62:
                    /*  4335 */ name = "Zestap'onis Raioni";
                    /*  4336 */ break;
                case 63:
                    /*  4338 */ name = "Zugdidi";
                    /*  4339 */ break;
                case 64:
                    /*  4341 */ name = "Zugdidis Raioni";
            }
        }

        /*  4345 */ if (country_code.equals("GH") == true) {
            /*  4346 */ switch (region_code2) {
                case 1:
                    /*  4348 */ name = "Greater Accra";
                    /*  4349 */ break;
                case 2:
                    /*  4351 */ name = "Ashanti";
                    /*  4352 */ break;
                case 3:
                    /*  4354 */ name = "Brong-Ahafo";
                    /*  4355 */ break;
                case 4:
                    /*  4357 */ name = "Central";
                    /*  4358 */ break;
                case 5:
                    /*  4360 */ name = "Eastern";
                    /*  4361 */ break;
                case 6:
                    /*  4363 */ name = "Northern";
                    /*  4364 */ break;
                case 8:
                    /*  4366 */ name = "Volta";
                    /*  4367 */ break;
                case 9:
                    /*  4369 */ name = "Western";
                    /*  4370 */ break;
                case 10:
                    /*  4372 */ name = "Upper East";
                    /*  4373 */ break;
                case 11:
                    /*  4375 */ name = "Upper West";
                case 7:
            }
        }
        /*  4379 */ if (country_code.equals("GL") == true) {
            /*  4380 */ switch (region_code2) {
                case 1:
                    /*  4382 */ name = "Nordgronland";
                    /*  4383 */ break;
                case 2:
                    /*  4385 */ name = "Ostgronland";
                    /*  4386 */ break;
                case 3:
                    /*  4388 */ name = "Vestgronland";
            }
        }

        /*  4392 */ if (country_code.equals("GM") == true) {
            /*  4393 */ switch (region_code2) {
                case 1:
                    /*  4395 */ name = "Banjul";
                    /*  4396 */ break;
                case 2:
                    /*  4398 */ name = "Lower River";
                    /*  4399 */ break;
                case 3:
                    /*  4401 */ name = "Central River";
                    /*  4402 */ break;
                case 4:
                    /*  4404 */ name = "Upper River";
                    /*  4405 */ break;
                case 5:
                    /*  4407 */ name = "Western";
                    /*  4408 */ break;
                case 7:
                    /*  4410 */ name = "North Bank";
                case 6:
            }
        }
        /*  4414 */ if (country_code.equals("GN") == true) /*  4415 */ {
            switch (region_code2) {
                case 1:
                    /*  4417 */ name = "Beyla";
                    /*  4418 */ break;
                case 2:
                    /*  4420 */ name = "Boffa";
                    /*  4421 */ break;
                case 3:
                    /*  4423 */ name = "Boke";
                    /*  4424 */ break;
                case 4:
                    /*  4426 */ name = "Conakry";
                    /*  4427 */ break;
                case 5:
                    /*  4429 */ name = "Dabola";
                    /*  4430 */ break;
                case 6:
                    /*  4432 */ name = "Dalaba";
                    /*  4433 */ break;
                case 7:
                    /*  4435 */ name = "Dinguiraye";
                    /*  4436 */ break;
                case 9:
                    /*  4438 */ name = "Faranah";
                    /*  4439 */ break;
                case 10:
                    /*  4441 */ name = "Forecariah";
                    /*  4442 */ break;
                case 11:
                    /*  4444 */ name = "Fria";
                    /*  4445 */ break;
                case 12:
                    /*  4447 */ name = "Gaoual";
                    /*  4448 */ break;
                case 13:
                    /*  4450 */ name = "Gueckedou";
                    /*  4451 */ break;
                case 15:
                    /*  4453 */ name = "Kerouane";
                    /*  4454 */ break;
                case 16:
                    /*  4456 */ name = "Kindia";
                    /*  4457 */ break;
                case 17:
                    /*  4459 */ name = "Kissidougou";
                    /*  4460 */ break;
                case 18:
                    /*  4462 */ name = "Koundara";
                    /*  4463 */ break;
                case 19:
                    /*  4465 */ name = "Kouroussa";
                    /*  4466 */ break;
                case 21:
                    /*  4468 */ name = "Macenta";
                    /*  4469 */ break;
                case 22:
                    /*  4471 */ name = "Mali";
                    /*  4472 */ break;
                case 23:
                    /*  4474 */ name = "Mamou";
                    /*  4475 */ break;
                case 25:
                    /*  4477 */ name = "Pita";
                    /*  4478 */ break;
                case 27:
                    /*  4480 */ name = "Telimele";
                    /*  4481 */ break;
                case 28:
                    /*  4483 */ name = "Tougue";
                    /*  4484 */ break;
                case 29:
                    /*  4486 */ name = "Yomou";
                    /*  4487 */ break;
                case 30:
                    /*  4489 */ name = "Coyah";
                    /*  4490 */ break;
                case 31:
                    /*  4492 */ name = "Dubreka";
                    /*  4493 */ break;
                case 32:
                    /*  4495 */ name = "Kankan";
                    /*  4496 */ break;
                case 33:
                    /*  4498 */ name = "Koubia";
                    /*  4499 */ break;
                case 34:
                    /*  4501 */ name = "Labe";
                    /*  4502 */ break;
                case 35:
                    /*  4504 */ name = "Lelouma";
                    /*  4505 */ break;
                case 36:
                    /*  4507 */ name = "Lola";
                    /*  4508 */ break;
                case 37:
                    /*  4510 */ name = "Mandiana";
                    /*  4511 */ break;
                case 38:
                    /*  4513 */ name = "Nzerekore";
                    /*  4514 */ break;
                case 39:
                    /*  4516 */ name = "Siguiri";
                case 8:
                case 14:
                case 20:
                case 24:
                /*  4520 */ case 26:
            }
        }
        if (country_code.equals("GQ") == true) {
            /*  4521 */ switch (region_code2) {
                case 3:
                    /*  4523 */ name = "Annobon";
                    /*  4524 */ break;
                case 4:
                    /*  4526 */ name = "Bioko Norte";
                    /*  4527 */ break;
                case 5:
                    /*  4529 */ name = "Bioko Sur";
                    /*  4530 */ break;
                case 6:
                    /*  4532 */ name = "Centro Sur";
                    /*  4533 */ break;
                case 7:
                    /*  4535 */ name = "Kie-Ntem";
                    /*  4536 */ break;
                case 8:
                    /*  4538 */ name = "Litoral";
                    /*  4539 */ break;
                case 9:
                    /*  4541 */ name = "Wele-Nzas";
            }
        }

        /*  4545 */ if (country_code.equals("GR") == true) {
            /*  4546 */ switch (region_code2) {
                case 1:
                    /*  4548 */ name = "Evros";
                    /*  4549 */ break;
                case 2:
                    /*  4551 */ name = "Rodhopi";
                    /*  4552 */ break;
                case 3:
                    /*  4554 */ name = "Xanthi";
                    /*  4555 */ break;
                case 4:
                    /*  4557 */ name = "Drama";
                    /*  4558 */ break;
                case 5:
                    /*  4560 */ name = "Serrai";
                    /*  4561 */ break;
                case 6:
                    /*  4563 */ name = "Kilkis";
                    /*  4564 */ break;
                case 7:
                    /*  4566 */ name = "Pella";
                    /*  4567 */ break;
                case 8:
                    /*  4569 */ name = "Florina";
                    /*  4570 */ break;
                case 9:
                    /*  4572 */ name = "Kastoria";
                    /*  4573 */ break;
                case 10:
                    /*  4575 */ name = "Grevena";
                    /*  4576 */ break;
                case 11:
                    /*  4578 */ name = "Kozani";
                    /*  4579 */ break;
                case 12:
                    /*  4581 */ name = "Imathia";
                    /*  4582 */ break;
                case 13:
                    /*  4584 */ name = "Thessaloniki";
                    /*  4585 */ break;
                case 14:
                    /*  4587 */ name = "Kavala";
                    /*  4588 */ break;
                case 15:
                    /*  4590 */ name = "Khalkidhiki";
                    /*  4591 */ break;
                case 16:
                    /*  4593 */ name = "Pieria";
                    /*  4594 */ break;
                case 17:
                    /*  4596 */ name = "Ioannina";
                    /*  4597 */ break;
                case 18:
                    /*  4599 */ name = "Thesprotia";
                    /*  4600 */ break;
                case 19:
                    /*  4602 */ name = "Preveza";
                    /*  4603 */ break;
                case 20:
                    /*  4605 */ name = "Arta";
                    /*  4606 */ break;
                case 21:
                    /*  4608 */ name = "Larisa";
                    /*  4609 */ break;
                case 22:
                    /*  4611 */ name = "Trikala";
                    /*  4612 */ break;
                case 23:
                    /*  4614 */ name = "Kardhitsa";
                    /*  4615 */ break;
                case 24:
                    /*  4617 */ name = "Magnisia";
                    /*  4618 */ break;
                case 25:
                    /*  4620 */ name = "Kerkira";
                    /*  4621 */ break;
                case 26:
                    /*  4623 */ name = "Levkas";
                    /*  4624 */ break;
                case 27:
                    /*  4626 */ name = "Kefallinia";
                    /*  4627 */ break;
                case 28:
                    /*  4629 */ name = "Zakinthos";
                    /*  4630 */ break;
                case 29:
                    /*  4632 */ name = "Fthiotis";
                    /*  4633 */ break;
                case 30:
                    /*  4635 */ name = "Evritania";
                    /*  4636 */ break;
                case 31:
                    /*  4638 */ name = "Aitolia kai Akarnania";
                    /*  4639 */ break;
                case 32:
                    /*  4641 */ name = "Fokis";
                    /*  4642 */ break;
                case 33:
                    /*  4644 */ name = "Voiotia";
                    /*  4645 */ break;
                case 34:
                    /*  4647 */ name = "Evvoia";
                    /*  4648 */ break;
                case 35:
                    /*  4650 */ name = "Attiki";
                    /*  4651 */ break;
                case 36:
                    /*  4653 */ name = "Argolis";
                    /*  4654 */ break;
                case 37:
                    /*  4656 */ name = "Korinthia";
                    /*  4657 */ break;
                case 38:
                    /*  4659 */ name = "Akhaia";
                    /*  4660 */ break;
                case 39:
                    /*  4662 */ name = "Ilia";
                    /*  4663 */ break;
                case 40:
                    /*  4665 */ name = "Messinia";
                    /*  4666 */ break;
                case 41:
                    /*  4668 */ name = "Arkadhia";
                    /*  4669 */ break;
                case 42:
                    /*  4671 */ name = "Lakonia";
                    /*  4672 */ break;
                case 43:
                    /*  4674 */ name = "Khania";
                    /*  4675 */ break;
                case 44:
                    /*  4677 */ name = "Rethimni";
                    /*  4678 */ break;
                case 45:
                    /*  4680 */ name = "Iraklion";
                    /*  4681 */ break;
                case 46:
                    /*  4683 */ name = "Lasithi";
                    /*  4684 */ break;
                case 47:
                    /*  4686 */ name = "Dhodhekanisos";
                    /*  4687 */ break;
                case 48:
                    /*  4689 */ name = "Samos";
                    /*  4690 */ break;
                case 49:
                    /*  4692 */ name = "Kikladhes";
                    /*  4693 */ break;
                case 50:
                    /*  4695 */ name = "Khios";
                    /*  4696 */ break;
                case 51:
                    /*  4698 */ name = "Lesvos";
            }
        }

        /*  4702 */ if (country_code.equals("GT") == true) {
            /*  4703 */ switch (region_code2) {
                case 1:
                    /*  4705 */ name = "Alta Verapaz";
                    /*  4706 */ break;
                case 2:
                    /*  4708 */ name = "Baja Verapaz";
                    /*  4709 */ break;
                case 3:
                    /*  4711 */ name = "Chimaltenango";
                    /*  4712 */ break;
                case 4:
                    /*  4714 */ name = "Chiquimula";
                    /*  4715 */ break;
                case 5:
                    /*  4717 */ name = "El Progreso";
                    /*  4718 */ break;
                case 6:
                    /*  4720 */ name = "Escuintla";
                    /*  4721 */ break;
                case 7:
                    /*  4723 */ name = "Guatemala";
                    /*  4724 */ break;
                case 8:
                    /*  4726 */ name = "Huehuetenango";
                    /*  4727 */ break;
                case 9:
                    /*  4729 */ name = "Izabal";
                    /*  4730 */ break;
                case 10:
                    /*  4732 */ name = "Jalapa";
                    /*  4733 */ break;
                case 11:
                    /*  4735 */ name = "Jutiapa";
                    /*  4736 */ break;
                case 12:
                    /*  4738 */ name = "Peten";
                    /*  4739 */ break;
                case 13:
                    /*  4741 */ name = "Quetzaltenango";
                    /*  4742 */ break;
                case 14:
                    /*  4744 */ name = "Quiche";
                    /*  4745 */ break;
                case 15:
                    /*  4747 */ name = "Retalhuleu";
                    /*  4748 */ break;
                case 16:
                    /*  4750 */ name = "Sacatepequez";
                    /*  4751 */ break;
                case 17:
                    /*  4753 */ name = "San Marcos";
                    /*  4754 */ break;
                case 18:
                    /*  4756 */ name = "Santa Rosa";
                    /*  4757 */ break;
                case 19:
                    /*  4759 */ name = "Solola";
                    /*  4760 */ break;
                case 20:
                    /*  4762 */ name = "Suchitepequez";
                    /*  4763 */ break;
                case 21:
                    /*  4765 */ name = "Totonicapan";
                    /*  4766 */ break;
                case 22:
                    /*  4768 */ name = "Zacapa";
            }
        }

        /*  4772 */ if (country_code.equals("GW") == true) /*  4773 */ {
            switch (region_code2) {
                case 1:
                    /*  4775 */ name = "Bafata";
                    /*  4776 */ break;
                case 2:
                    /*  4778 */ name = "Quinara";
                    /*  4779 */ break;
                case 4:
                    /*  4781 */ name = "Oio";
                    /*  4782 */ break;
                case 5:
                    /*  4784 */ name = "Bolama";
                    /*  4785 */ break;
                case 6:
                    /*  4787 */ name = "Cacheu";
                    /*  4788 */ break;
                case 7:
                    /*  4790 */ name = "Tombali";
                    /*  4791 */ break;
                case 10:
                    /*  4793 */ name = "Gabu";
                    /*  4794 */ break;
                case 11:
                    /*  4796 */ name = "Bissau";
                    /*  4797 */ break;
                case 12:
                    /*  4799 */ name = "Biombo";
                case 3:
                case 8:
                case 9:
            }
        }
        /*  4803 */ if (country_code.equals("GY") == true) {
            /*  4804 */ switch (region_code2) {
                case 10:
                    /*  4806 */ name = "Barima-Waini";
                    /*  4807 */ break;
                case 11:
                    /*  4809 */ name = "Cuyuni-Mazaruni";
                    /*  4810 */ break;
                case 12:
                    /*  4812 */ name = "Demerara-Mahaica";
                    /*  4813 */ break;
                case 13:
                    /*  4815 */ name = "East Berbice-Corentyne";
                    /*  4816 */ break;
                case 14:
                    /*  4818 */ name = "Essequibo Islands-West Demerara";
                    /*  4819 */ break;
                case 15:
                    /*  4821 */ name = "Mahaica-Berbice";
                    /*  4822 */ break;
                case 16:
                    /*  4824 */ name = "Pomeroon-Supenaam";
                    /*  4825 */ break;
                case 17:
                    /*  4827 */ name = "Potaro-Siparuni";
                    /*  4828 */ break;
                case 18:
                    /*  4830 */ name = "Upper Demerara-Berbice";
                    /*  4831 */ break;
                case 19:
                    /*  4833 */ name = "Upper Takutu-Upper Essequibo";
            }
        }

        /*  4837 */ if (country_code.equals("HN") == true) {
            /*  4838 */ switch (region_code2) {
                case 1:
                    /*  4840 */ name = "Atlantida";
                    /*  4841 */ break;
                case 2:
                    /*  4843 */ name = "Choluteca";
                    /*  4844 */ break;
                case 3:
                    /*  4846 */ name = "Colon";
                    /*  4847 */ break;
                case 4:
                    /*  4849 */ name = "Comayagua";
                    /*  4850 */ break;
                case 5:
                    /*  4852 */ name = "Copan";
                    /*  4853 */ break;
                case 6:
                    /*  4855 */ name = "Cortes";
                    /*  4856 */ break;
                case 7:
                    /*  4858 */ name = "El Paraiso";
                    /*  4859 */ break;
                case 8:
                    /*  4861 */ name = "Francisco Morazan";
                    /*  4862 */ break;
                case 9:
                    /*  4864 */ name = "Gracias a Dios";
                    /*  4865 */ break;
                case 10:
                    /*  4867 */ name = "Intibuca";
                    /*  4868 */ break;
                case 11:
                    /*  4870 */ name = "Islas de la Bahia";
                    /*  4871 */ break;
                case 12:
                    /*  4873 */ name = "La Paz";
                    /*  4874 */ break;
                case 13:
                    /*  4876 */ name = "Lempira";
                    /*  4877 */ break;
                case 14:
                    /*  4879 */ name = "Ocotepeque";
                    /*  4880 */ break;
                case 15:
                    /*  4882 */ name = "Olancho";
                    /*  4883 */ break;
                case 16:
                    /*  4885 */ name = "Santa Barbara";
                    /*  4886 */ break;
                case 17:
                    /*  4888 */ name = "Valle";
                    /*  4889 */ break;
                case 18:
                    /*  4891 */ name = "Yoro";
            }
        }

        /*  4895 */ if (country_code.equals("HR") == true) {
            /*  4896 */ switch (region_code2) {
                case 1:
                    /*  4898 */ name = "Bjelovarsko-Bilogorska";
                    /*  4899 */ break;
                case 2:
                    /*  4901 */ name = "Brodsko-Posavska";
                    /*  4902 */ break;
                case 3:
                    /*  4904 */ name = "Dubrovacko-Neretvanska";
                    /*  4905 */ break;
                case 4:
                    /*  4907 */ name = "Istarska";
                    /*  4908 */ break;
                case 5:
                    /*  4910 */ name = "Karlovacka";
                    /*  4911 */ break;
                case 6:
                    /*  4913 */ name = "Koprivnicko-Krizevacka";
                    /*  4914 */ break;
                case 7:
                    /*  4916 */ name = "Krapinsko-Zagorska";
                    /*  4917 */ break;
                case 8:
                    /*  4919 */ name = "Licko-Senjska";
                    /*  4920 */ break;
                case 9:
                    /*  4922 */ name = "Medimurska";
                    /*  4923 */ break;
                case 10:
                    /*  4925 */ name = "Osjecko-Baranjska";
                    /*  4926 */ break;
                case 11:
                    /*  4928 */ name = "Pozesko-Slavonska";
                    /*  4929 */ break;
                case 12:
                    /*  4931 */ name = "Primorsko-Goranska";
                    /*  4932 */ break;
                case 13:
                    /*  4934 */ name = "Sibensko-Kninska";
                    /*  4935 */ break;
                case 14:
                    /*  4937 */ name = "Sisacko-Moslavacka";
                    /*  4938 */ break;
                case 15:
                    /*  4940 */ name = "Splitsko-Dalmatinska";
                    /*  4941 */ break;
                case 16:
                    /*  4943 */ name = "Varazdinska";
                    /*  4944 */ break;
                case 17:
                    /*  4946 */ name = "Viroviticko-Podravska";
                    /*  4947 */ break;
                case 18:
                    /*  4949 */ name = "Vukovarsko-Srijemska";
                    /*  4950 */ break;
                case 19:
                    /*  4952 */ name = "Zadarska";
                    /*  4953 */ break;
                case 20:
                    /*  4955 */ name = "Zagrebacka";
                    /*  4956 */ break;
                case 21:
                    /*  4958 */ name = "Grad Zagreb";
            }
        }

        /*  4962 */ if (country_code.equals("HT") == true) /*  4963 */ {
            switch (region_code2) {
                case 3:
                    /*  4965 */ name = "Nord-Ouest";
                    /*  4966 */ break;
                case 6:
                    /*  4968 */ name = "Artibonite";
                    /*  4969 */ break;
                case 7:
                    /*  4971 */ name = "Centre";
                    /*  4972 */ break;
                case 9:
                    /*  4974 */ name = "Nord";
                    /*  4975 */ break;
                case 10:
                    /*  4977 */ name = "Nord-Est";
                    /*  4978 */ break;
                case 11:
                    /*  4980 */ name = "Ouest";
                    /*  4981 */ break;
                case 12:
                    /*  4983 */ name = "Sud";
                    /*  4984 */ break;
                case 13:
                    /*  4986 */ name = "Sud-Est";
                    /*  4987 */ break;
                case 14:
                    /*  4989 */ name = "Grand' Anse";
                    /*  4990 */ break;
                case 15:
                    /*  4992 */ name = "Nippes";
                case 4:
                case 5:
                case 8:
            }
        }
        /*  4996 */ if (country_code.equals("HU") == true) {
            /*  4997 */ switch (region_code2) {
                case 1:
                    /*  4999 */ name = "Bacs-Kiskun";
                    /*  5000 */ break;
                case 2:
                    /*  5002 */ name = "Baranya";
                    /*  5003 */ break;
                case 3:
                    /*  5005 */ name = "Bekes";
                    /*  5006 */ break;
                case 4:
                    /*  5008 */ name = "Borsod-Abauj-Zemplen";
                    /*  5009 */ break;
                case 5:
                    /*  5011 */ name = "Budapest";
                    /*  5012 */ break;
                case 6:
                    /*  5014 */ name = "Csongrad";
                    /*  5015 */ break;
                case 7:
                    /*  5017 */ name = "Debrecen";
                    /*  5018 */ break;
                case 8:
                    /*  5020 */ name = "Fejer";
                    /*  5021 */ break;
                case 9:
                    /*  5023 */ name = "Gyor-Moson-Sopron";
                    /*  5024 */ break;
                case 10:
                    /*  5026 */ name = "Hajdu-Bihar";
                    /*  5027 */ break;
                case 11:
                    /*  5029 */ name = "Heves";
                    /*  5030 */ break;
                case 12:
                    /*  5032 */ name = "Komarom-Esztergom";
                    /*  5033 */ break;
                case 13:
                    /*  5035 */ name = "Miskolc";
                    /*  5036 */ break;
                case 14:
                    /*  5038 */ name = "Nograd";
                    /*  5039 */ break;
                case 15:
                    /*  5041 */ name = "Pecs";
                    /*  5042 */ break;
                case 16:
                    /*  5044 */ name = "Pest";
                    /*  5045 */ break;
                case 17:
                    /*  5047 */ name = "Somogy";
                    /*  5048 */ break;
                case 18:
                    /*  5050 */ name = "Szabolcs-Szatmar-Bereg";
                    /*  5051 */ break;
                case 19:
                    /*  5053 */ name = "Szeged";
                    /*  5054 */ break;
                case 20:
                    /*  5056 */ name = "Jasz-Nagykun-Szolnok";
                    /*  5057 */ break;
                case 21:
                    /*  5059 */ name = "Tolna";
                    /*  5060 */ break;
                case 22:
                    /*  5062 */ name = "Vas";
                    /*  5063 */ break;
                case 23:
                    /*  5065 */ name = "Veszprem";
                    /*  5066 */ break;
                case 24:
                    /*  5068 */ name = "Zala";
                    /*  5069 */ break;
                case 25:
                    /*  5071 */ name = "Gyor";
                    /*  5072 */ break;
                case 26:
                    /*  5074 */ name = "Bekescsaba";
                    /*  5075 */ break;
                case 27:
                    /*  5077 */ name = "Dunaujvaros";
                    /*  5078 */ break;
                case 28:
                    /*  5080 */ name = "Eger";
                    /*  5081 */ break;
                case 29:
                    /*  5083 */ name = "Hodmezovasarhely";
                    /*  5084 */ break;
                case 30:
                    /*  5086 */ name = "Kaposvar";
                    /*  5087 */ break;
                case 31:
                    /*  5089 */ name = "Kecskemet";
                    /*  5090 */ break;
                case 32:
                    /*  5092 */ name = "Nagykanizsa";
                    /*  5093 */ break;
                case 33:
                    /*  5095 */ name = "Nyiregyhaza";
                    /*  5096 */ break;
                case 34:
                    /*  5098 */ name = "Sopron";
                    /*  5099 */ break;
                case 35:
                    /*  5101 */ name = "Szekesfehervar";
                    /*  5102 */ break;
                case 36:
                    /*  5104 */ name = "Szolnok";
                    /*  5105 */ break;
                case 37:
                    /*  5107 */ name = "Szombathely";
                    /*  5108 */ break;
                case 38:
                    /*  5110 */ name = "Tatabanya";
                    /*  5111 */ break;
                case 39:
                    /*  5113 */ name = "Veszprem";
                    /*  5114 */ break;
                case 40:
                    /*  5116 */ name = "Zalaegerszeg";
                    /*  5117 */ break;
                case 41:
                    /*  5119 */ name = "Salgotarjan";
                    /*  5120 */ break;
                case 42:
                    /*  5122 */ name = "Szekszard";
                    /*  5123 */ break;
                case 43:
                    /*  5125 */ name = "Erd";
            }
        }

        /*  5129 */ if (country_code.equals("ID") == true) /*  5130 */ {
            switch (region_code2) {
                case 1:
                    /*  5132 */ name = "Aceh";
                    /*  5133 */ break;
                case 2:
                    /*  5135 */ name = "Bali";
                    /*  5136 */ break;
                case 3:
                    /*  5138 */ name = "Bengkulu";
                    /*  5139 */ break;
                case 4:
                    /*  5141 */ name = "Jakarta Raya";
                    /*  5142 */ break;
                case 5:
                    /*  5144 */ name = "Jambi";
                    /*  5145 */ break;
                case 7:
                    /*  5147 */ name = "Jawa Tengah";
                    /*  5148 */ break;
                case 8:
                    /*  5150 */ name = "Jawa Timur";
                    /*  5151 */ break;
                case 10:
                    /*  5153 */ name = "Yogyakarta";
                    /*  5154 */ break;
                case 11:
                    /*  5156 */ name = "Kalimantan Barat";
                    /*  5157 */ break;
                case 12:
                    /*  5159 */ name = "Kalimantan Selatan";
                    /*  5160 */ break;
                case 13:
                    /*  5162 */ name = "Kalimantan Tengah";
                    /*  5163 */ break;
                case 14:
                    /*  5165 */ name = "Kalimantan Timur";
                    /*  5166 */ break;
                case 15:
                    /*  5168 */ name = "Lampung";
                    /*  5169 */ break;
                case 17:
                    /*  5171 */ name = "Nusa Tenggara Barat";
                    /*  5172 */ break;
                case 18:
                    /*  5174 */ name = "Nusa Tenggara Timur";
                    /*  5175 */ break;
                case 21:
                    /*  5177 */ name = "Sulawesi Tengah";
                    /*  5178 */ break;
                case 22:
                    /*  5180 */ name = "Sulawesi Tenggara";
                    /*  5181 */ break;
                case 24:
                    /*  5183 */ name = "Sumatera Barat";
                    /*  5184 */ break;
                case 26:
                    /*  5186 */ name = "Sumatera Utara";
                    /*  5187 */ break;
                case 28:
                    /*  5189 */ name = "Maluku";
                    /*  5190 */ break;
                case 29:
                    /*  5192 */ name = "Maluku Utara";
                    /*  5193 */ break;
                case 30:
                    /*  5195 */ name = "Jawa Barat";
                    /*  5196 */ break;
                case 31:
                    /*  5198 */ name = "Sulawesi Utara";
                    /*  5199 */ break;
                case 32:
                    /*  5201 */ name = "Sumatera Selatan";
                    /*  5202 */ break;
                case 33:
                    /*  5204 */ name = "Banten";
                    /*  5205 */ break;
                case 34:
                    /*  5207 */ name = "Gorontalo";
                    /*  5208 */ break;
                case 35:
                    /*  5210 */ name = "Kepulauan Bangka Belitung";
                    /*  5211 */ break;
                case 36:
                    /*  5213 */ name = "Papua";
                    /*  5214 */ break;
                case 37:
                    /*  5216 */ name = "Riau";
                    /*  5217 */ break;
                case 38:
                    /*  5219 */ name = "Sulawesi Selatan";
                    /*  5220 */ break;
                case 39:
                    /*  5222 */ name = "Irian Jaya Barat";
                    /*  5223 */ break;
                case 40:
                    /*  5225 */ name = "Kepulauan Riau";
                    /*  5226 */ break;
                case 41:
                    /*  5228 */ name = "Sulawesi Barat";
                case 6:
                case 9:
                case 16:
                case 19:
                case 20:
                case 23:
                case 25:
                /*  5232 */ case 27:
            }
        }
        if (country_code.equals("IE") == true) /*  5233 */ {
            switch (region_code2) {
                case 1:
                    /*  5235 */ name = "Carlow";
                    /*  5236 */ break;
                case 2:
                    /*  5238 */ name = "Cavan";
                    /*  5239 */ break;
                case 3:
                    /*  5241 */ name = "Clare";
                    /*  5242 */ break;
                case 4:
                    /*  5244 */ name = "Cork";
                    /*  5245 */ break;
                case 6:
                    /*  5247 */ name = "Donegal";
                    /*  5248 */ break;
                case 7:
                    /*  5250 */ name = "Dublin";
                    /*  5251 */ break;
                case 10:
                    /*  5253 */ name = "Galway";
                    /*  5254 */ break;
                case 11:
                    /*  5256 */ name = "Kerry";
                    /*  5257 */ break;
                case 12:
                    /*  5259 */ name = "Kildare";
                    /*  5260 */ break;
                case 13:
                    /*  5262 */ name = "Kilkenny";
                    /*  5263 */ break;
                case 14:
                    /*  5265 */ name = "Leitrim";
                    /*  5266 */ break;
                case 15:
                    /*  5268 */ name = "Laois";
                    /*  5269 */ break;
                case 16:
                    /*  5271 */ name = "Limerick";
                    /*  5272 */ break;
                case 18:
                    /*  5274 */ name = "Longford";
                    /*  5275 */ break;
                case 19:
                    /*  5277 */ name = "Louth";
                    /*  5278 */ break;
                case 20:
                    /*  5280 */ name = "Mayo";
                    /*  5281 */ break;
                case 21:
                    /*  5283 */ name = "Meath";
                    /*  5284 */ break;
                case 22:
                    /*  5286 */ name = "Monaghan";
                    /*  5287 */ break;
                case 23:
                    /*  5289 */ name = "Offaly";
                    /*  5290 */ break;
                case 24:
                    /*  5292 */ name = "Roscommon";
                    /*  5293 */ break;
                case 25:
                    /*  5295 */ name = "Sligo";
                    /*  5296 */ break;
                case 26:
                    /*  5298 */ name = "Tipperary";
                    /*  5299 */ break;
                case 27:
                    /*  5301 */ name = "Waterford";
                    /*  5302 */ break;
                case 29:
                    /*  5304 */ name = "Westmeath";
                    /*  5305 */ break;
                case 30:
                    /*  5307 */ name = "Wexford";
                    /*  5308 */ break;
                case 31:
                    /*  5310 */ name = "Wicklow";
                case 5:
                case 8:
                case 9:
                case 17:
                /*  5314 */ case 28:
            }
        }
        if (country_code.equals("IL") == true) {
            /*  5315 */ switch (region_code2) {
                case 1:
                    /*  5317 */ name = "HaDarom";
                    /*  5318 */ break;
                case 2:
                    /*  5320 */ name = "HaMerkaz";
                    /*  5321 */ break;
                case 3:
                    /*  5323 */ name = "HaZafon";
                    /*  5324 */ break;
                case 4:
                    /*  5326 */ name = "Hefa";
                    /*  5327 */ break;
                case 5:
                    /*  5329 */ name = "Tel Aviv";
                    /*  5330 */ break;
                case 6:
                    /*  5332 */ name = "Yerushalayim";
            }
        }

        /*  5336 */ if (country_code.equals("IN") == true) /*  5337 */ {
            switch (region_code2) {
                case 1:
                    /*  5339 */ name = "Andaman and Nicobar Islands";
                    /*  5340 */ break;
                case 2:
                    /*  5342 */ name = "Andhra Pradesh";
                    /*  5343 */ break;
                case 3:
                    /*  5345 */ name = "Assam";
                    /*  5346 */ break;
                case 5:
                    /*  5348 */ name = "Chandigarh";
                    /*  5349 */ break;
                case 6:
                    /*  5351 */ name = "Dadra and Nagar Haveli";
                    /*  5352 */ break;
                case 7:
                    /*  5354 */ name = "Delhi";
                    /*  5355 */ break;
                case 9:
                    /*  5357 */ name = "Gujarat";
                    /*  5358 */ break;
                case 10:
                    /*  5360 */ name = "Haryana";
                    /*  5361 */ break;
                case 11:
                    /*  5363 */ name = "Himachal Pradesh";
                    /*  5364 */ break;
                case 12:
                    /*  5366 */ name = "Jammu and Kashmir";
                    /*  5367 */ break;
                case 13:
                    /*  5369 */ name = "Kerala";
                    /*  5370 */ break;
                case 14:
                    /*  5372 */ name = "Lakshadweep";
                    /*  5373 */ break;
                case 16:
                    /*  5375 */ name = "Maharashtra";
                    /*  5376 */ break;
                case 17:
                    /*  5378 */ name = "Manipur";
                    /*  5379 */ break;
                case 18:
                    /*  5381 */ name = "Meghalaya";
                    /*  5382 */ break;
                case 19:
                    /*  5384 */ name = "Karnataka";
                    /*  5385 */ break;
                case 20:
                    /*  5387 */ name = "Nagaland";
                    /*  5388 */ break;
                case 21:
                    /*  5390 */ name = "Orissa";
                    /*  5391 */ break;
                case 22:
                    /*  5393 */ name = "Puducherry";
                    /*  5394 */ break;
                case 23:
                    /*  5396 */ name = "Punjab";
                    /*  5397 */ break;
                case 24:
                    /*  5399 */ name = "Rajasthan";
                    /*  5400 */ break;
                case 25:
                    /*  5402 */ name = "Tamil Nadu";
                    /*  5403 */ break;
                case 26:
                    /*  5405 */ name = "Tripura";
                    /*  5406 */ break;
                case 28:
                    /*  5408 */ name = "West Bengal";
                    /*  5409 */ break;
                case 29:
                    /*  5411 */ name = "Sikkim";
                    /*  5412 */ break;
                case 30:
                    /*  5414 */ name = "Arunachal Pradesh";
                    /*  5415 */ break;
                case 31:
                    /*  5417 */ name = "Mizoram";
                    /*  5418 */ break;
                case 32:
                    /*  5420 */ name = "Daman and Diu";
                    /*  5421 */ break;
                case 33:
                    /*  5423 */ name = "Goa";
                    /*  5424 */ break;
                case 34:
                    /*  5426 */ name = "Bihar";
                    /*  5427 */ break;
                case 35:
                    /*  5429 */ name = "Madhya Pradesh";
                    /*  5430 */ break;
                case 36:
                    /*  5432 */ name = "Uttar Pradesh";
                    /*  5433 */ break;
                case 37:
                    /*  5435 */ name = "Chhattisgarh";
                    /*  5436 */ break;
                case 38:
                    /*  5438 */ name = "Jharkhand";
                    /*  5439 */ break;
                case 39:
                    /*  5441 */ name = "Uttarakhand";
                case 4:
                case 8:
                case 15:
                /*  5445 */ case 27:
            }
        }
        if (country_code.equals("IQ") == true) {
            /*  5446 */ switch (region_code2) {
                case 1:
                    /*  5448 */ name = "Al Anbar";
                    /*  5449 */ break;
                case 2:
                    /*  5451 */ name = "Al Basrah";
                    /*  5452 */ break;
                case 3:
                    /*  5454 */ name = "Al Muthanna";
                    /*  5455 */ break;
                case 4:
                    /*  5457 */ name = "Al Qadisiyah";
                    /*  5458 */ break;
                case 5:
                    /*  5460 */ name = "As Sulaymaniyah";
                    /*  5461 */ break;
                case 6:
                    /*  5463 */ name = "Babil";
                    /*  5464 */ break;
                case 7:
                    /*  5466 */ name = "Baghdad";
                    /*  5467 */ break;
                case 8:
                    /*  5469 */ name = "Dahuk";
                    /*  5470 */ break;
                case 9:
                    /*  5472 */ name = "Dhi Qar";
                    /*  5473 */ break;
                case 10:
                    /*  5475 */ name = "Diyala";
                    /*  5476 */ break;
                case 11:
                    /*  5478 */ name = "Arbil";
                    /*  5479 */ break;
                case 12:
                    /*  5481 */ name = "Karbala'";
                    /*  5482 */ break;
                case 13:
                    /*  5484 */ name = "At Ta'mim";
                    /*  5485 */ break;
                case 14:
                    /*  5487 */ name = "Maysan";
                    /*  5488 */ break;
                case 15:
                    /*  5490 */ name = "Ninawa";
                    /*  5491 */ break;
                case 16:
                    /*  5493 */ name = "Wasit";
                    /*  5494 */ break;
                case 17:
                    /*  5496 */ name = "An Najaf";
                    /*  5497 */ break;
                case 18:
                    /*  5499 */ name = "Salah ad Din";
            }
        }

        /*  5503 */ if (country_code.equals("IR") == true) /*  5504 */ {
            switch (region_code2) {
                case 1:
                    /*  5506 */ name = "Azarbayjan-e Bakhtari";
                    /*  5507 */ break;
                case 3:
                    /*  5509 */ name = "Chahar Mahall va Bakhtiari";
                    /*  5510 */ break;
                case 4:
                    /*  5512 */ name = "Sistan va Baluchestan";
                    /*  5513 */ break;
                case 5:
                    /*  5515 */ name = "Kohkiluyeh va Buyer Ahmadi";
                    /*  5516 */ break;
                case 7:
                    /*  5518 */ name = "Fars";
                    /*  5519 */ break;
                case 8:
                    /*  5521 */ name = "Gilan";
                    /*  5522 */ break;
                case 9:
                    /*  5524 */ name = "Hamadan";
                    /*  5525 */ break;
                case 10:
                    /*  5527 */ name = "Ilam";
                    /*  5528 */ break;
                case 11:
                    /*  5530 */ name = "Hormozgan";
                    /*  5531 */ break;
                case 12:
                    /*  5533 */ name = "Kerman";
                    /*  5534 */ break;
                case 13:
                    /*  5536 */ name = "Bakhtaran";
                    /*  5537 */ break;
                case 15:
                    /*  5539 */ name = "Khuzestan";
                    /*  5540 */ break;
                case 16:
                    /*  5542 */ name = "Kordestan";
                    /*  5543 */ break;
                case 17:
                    /*  5545 */ name = "Mazandaran";
                    /*  5546 */ break;
                case 18:
                    /*  5548 */ name = "Semnan Province";
                    /*  5549 */ break;
                case 19:
                    /*  5551 */ name = "Markazi";
                    /*  5552 */ break;
                case 21:
                    /*  5554 */ name = "Zanjan";
                    /*  5555 */ break;
                case 22:
                    /*  5557 */ name = "Bushehr";
                    /*  5558 */ break;
                case 23:
                    /*  5560 */ name = "Lorestan";
                    /*  5561 */ break;
                case 24:
                    /*  5563 */ name = "Markazi";
                    /*  5564 */ break;
                case 25:
                    /*  5566 */ name = "Semnan";
                    /*  5567 */ break;
                case 26:
                    /*  5569 */ name = "Tehran";
                    /*  5570 */ break;
                case 27:
                    /*  5572 */ name = "Zanjan";
                    /*  5573 */ break;
                case 28:
                    /*  5575 */ name = "Esfahan";
                    /*  5576 */ break;
                case 29:
                    /*  5578 */ name = "Kerman";
                    /*  5579 */ break;
                case 30:
                    /*  5581 */ name = "Khorasan";
                    /*  5582 */ break;
                case 31:
                    /*  5584 */ name = "Yazd";
                    /*  5585 */ break;
                case 32:
                    /*  5587 */ name = "Ardabil";
                    /*  5588 */ break;
                case 33:
                    /*  5590 */ name = "East Azarbaijan";
                    /*  5591 */ break;
                case 34:
                    /*  5593 */ name = "Markazi";
                    /*  5594 */ break;
                case 35:
                    /*  5596 */ name = "Mazandaran";
                    /*  5597 */ break;
                case 36:
                    /*  5599 */ name = "Zanjan";
                    /*  5600 */ break;
                case 37:
                    /*  5602 */ name = "Golestan";
                    /*  5603 */ break;
                case 38:
                    /*  5605 */ name = "Qazvin";
                    /*  5606 */ break;
                case 39:
                    /*  5608 */ name = "Qom";
                    /*  5609 */ break;
                case 40:
                    /*  5611 */ name = "Yazd";
                    /*  5612 */ break;
                case 41:
                    /*  5614 */ name = "Khorasan-e Janubi";
                    /*  5615 */ break;
                case 42:
                    /*  5617 */ name = "Khorasan-e Razavi";
                    /*  5618 */ break;
                case 43:
                    /*  5620 */ name = "Khorasan-e Shemali";
                case 2:
                case 6:
                case 14:
                /*  5624 */ case 20:
            }
        }
        if (country_code.equals("IS") == true) /*  5625 */ {
            switch (region_code2) {
                case 3:
                    /*  5627 */ name = "Arnessysla";
                    /*  5628 */ break;
                case 5:
                    /*  5630 */ name = "Austur-Hunavatnssysla";
                    /*  5631 */ break;
                case 6:
                    /*  5633 */ name = "Austur-Skaftafellssysla";
                    /*  5634 */ break;
                case 7:
                    /*  5636 */ name = "Borgarfjardarsysla";
                    /*  5637 */ break;
                case 9:
                    /*  5639 */ name = "Eyjafjardarsysla";
                    /*  5640 */ break;
                case 10:
                    /*  5642 */ name = "Gullbringusysla";
                    /*  5643 */ break;
                case 15:
                    /*  5645 */ name = "Kjosarsysla";
                    /*  5646 */ break;
                case 17:
                    /*  5648 */ name = "Myrasysla";
                    /*  5649 */ break;
                case 20:
                    /*  5651 */ name = "Nordur-Mulasysla";
                    /*  5652 */ break;
                case 21:
                    /*  5654 */ name = "Nordur-Tingeyjarsysla";
                    /*  5655 */ break;
                case 23:
                    /*  5657 */ name = "Rangarvallasysla";
                    /*  5658 */ break;
                case 28:
                    /*  5660 */ name = "Skagafjardarsysla";
                    /*  5661 */ break;
                case 29:
                    /*  5663 */ name = "Snafellsnes- og Hnappadalssysla";
                    /*  5664 */ break;
                case 30:
                    /*  5666 */ name = "Strandasysla";
                    /*  5667 */ break;
                case 31:
                    /*  5669 */ name = "Sudur-Mulasysla";
                    /*  5670 */ break;
                case 32:
                    /*  5672 */ name = "Sudur-Tingeyjarsysla";
                    /*  5673 */ break;
                case 34:
                    /*  5675 */ name = "Vestur-Bardastrandarsysla";
                    /*  5676 */ break;
                case 35:
                    /*  5678 */ name = "Vestur-Hunavatnssysla";
                    /*  5679 */ break;
                case 36:
                    /*  5681 */ name = "Vestur-Isafjardarsysla";
                    /*  5682 */ break;
                case 37:
                    /*  5684 */ name = "Vestur-Skaftafellssysla";
                    /*  5685 */ break;
                case 40:
                    /*  5687 */ name = "Norourland Eystra";
                    /*  5688 */ break;
                case 41:
                    /*  5690 */ name = "Norourland Vestra";
                    /*  5691 */ break;
                case 42:
                    /*  5693 */ name = "Suourland";
                    /*  5694 */ break;
                case 43:
                    /*  5696 */ name = "Suournes";
                    /*  5697 */ break;
                case 44:
                    /*  5699 */ name = "Vestfiroir";
                    /*  5700 */ break;
                case 45:
                    /*  5702 */ name = "Vesturland";
                case 4:
                case 8:
                case 11:
                case 12:
                case 13:
                case 14:
                case 16:
                case 18:
                case 19:
                case 22:
                case 24:
                case 25:
                case 26:
                case 27:
                case 33:
                case 38:
                /*  5706 */ case 39:
            }
        }
        if (country_code.equals("IT") == true) {
            /*  5707 */ switch (region_code2) {
                case 1:
                    /*  5709 */ name = "Abruzzi";
                    /*  5710 */ break;
                case 2:
                    /*  5712 */ name = "Basilicata";
                    /*  5713 */ break;
                case 3:
                    /*  5715 */ name = "Calabria";
                    /*  5716 */ break;
                case 4:
                    /*  5718 */ name = "Campania";
                    /*  5719 */ break;
                case 5:
                    /*  5721 */ name = "Emilia-Romagna";
                    /*  5722 */ break;
                case 6:
                    /*  5724 */ name = "Friuli-Venezia Giulia";
                    /*  5725 */ break;
                case 7:
                    /*  5727 */ name = "Lazio";
                    /*  5728 */ break;
                case 8:
                    /*  5730 */ name = "Liguria";
                    /*  5731 */ break;
                case 9:
                    /*  5733 */ name = "Lombardia";
                    /*  5734 */ break;
                case 10:
                    /*  5736 */ name = "Marche";
                    /*  5737 */ break;
                case 11:
                    /*  5739 */ name = "Molise";
                    /*  5740 */ break;
                case 12:
                    /*  5742 */ name = "Piemonte";
                    /*  5743 */ break;
                case 13:
                    /*  5745 */ name = "Puglia";
                    /*  5746 */ break;
                case 14:
                    /*  5748 */ name = "Sardegna";
                    /*  5749 */ break;
                case 15:
                    /*  5751 */ name = "Sicilia";
                    /*  5752 */ break;
                case 16:
                    /*  5754 */ name = "Toscana";
                    /*  5755 */ break;
                case 17:
                    /*  5757 */ name = "Trentino-Alto Adige";
                    /*  5758 */ break;
                case 18:
                    /*  5760 */ name = "Umbria";
                    /*  5761 */ break;
                case 19:
                    /*  5763 */ name = "Valle d'Aosta";
                    /*  5764 */ break;
                case 20:
                    /*  5766 */ name = "Veneto";
            }
        }

        /*  5770 */ if (country_code.equals("JM") == true) /*  5771 */ {
            switch (region_code2) {
                case 1:
                    /*  5773 */ name = "Clarendon";
                    /*  5774 */ break;
                case 2:
                    /*  5776 */ name = "Hanover";
                    /*  5777 */ break;
                case 4:
                    /*  5779 */ name = "Manchester";
                    /*  5780 */ break;
                case 7:
                    /*  5782 */ name = "Portland";
                    /*  5783 */ break;
                case 8:
                    /*  5785 */ name = "Saint Andrew";
                    /*  5786 */ break;
                case 9:
                    /*  5788 */ name = "Saint Ann";
                    /*  5789 */ break;
                case 10:
                    /*  5791 */ name = "Saint Catherine";
                    /*  5792 */ break;
                case 11:
                    /*  5794 */ name = "Saint Elizabeth";
                    /*  5795 */ break;
                case 12:
                    /*  5797 */ name = "Saint James";
                    /*  5798 */ break;
                case 13:
                    /*  5800 */ name = "Saint Mary";
                    /*  5801 */ break;
                case 14:
                    /*  5803 */ name = "Saint Thomas";
                    /*  5804 */ break;
                case 15:
                    /*  5806 */ name = "Trelawny";
                    /*  5807 */ break;
                case 16:
                    /*  5809 */ name = "Westmoreland";
                    /*  5810 */ break;
                case 17:
                    /*  5812 */ name = "Kingston";
                case 3:
                case 5:
                case 6:
            }
        }
        /*  5816 */ if (country_code.equals("JO") == true) /*  5817 */ {
            switch (region_code2) {
                case 2:
                    /*  5819 */ name = "Al Balqa'";
                    /*  5820 */ break;
                case 9:
                    /*  5822 */ name = "Al Karak";
                    /*  5823 */ break;
                case 12:
                    /*  5825 */ name = "At Tafilah";
                    /*  5826 */ break;
                case 15:
                    /*  5828 */ name = "Al Mafraq";
                    /*  5829 */ break;
                case 16:
                    /*  5831 */ name = "Amman";
                    /*  5832 */ break;
                case 17:
                    /*  5834 */ name = "Az Zaraqa";
                    /*  5835 */ break;
                case 18:
                    /*  5837 */ name = "Irbid";
                    /*  5838 */ break;
                case 19:
                    /*  5840 */ name = "Ma'an";
                    /*  5841 */ break;
                case 20:
                    /*  5843 */ name = "Ajlun";
                    /*  5844 */ break;
                case 21:
                    /*  5846 */ name = "Al Aqabah";
                    /*  5847 */ break;
                case 22:
                    /*  5849 */ name = "Jarash";
                    /*  5850 */ break;
                case 23:
                    /*  5852 */ name = "Madaba";
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 13:
                /*  5856 */ case 14:
            }
        }
        if (country_code.equals("JP") == true) {
            /*  5857 */ switch (region_code2) {
                case 1:
                    /*  5859 */ name = "Aichi";
                    /*  5860 */ break;
                case 2:
                    /*  5862 */ name = "Akita";
                    /*  5863 */ break;
                case 3:
                    /*  5865 */ name = "Aomori";
                    /*  5866 */ break;
                case 4:
                    /*  5868 */ name = "Chiba";
                    /*  5869 */ break;
                case 5:
                    /*  5871 */ name = "Ehime";
                    /*  5872 */ break;
                case 6:
                    /*  5874 */ name = "Fukui";
                    /*  5875 */ break;
                case 7:
                    /*  5877 */ name = "Fukuoka";
                    /*  5878 */ break;
                case 8:
                    /*  5880 */ name = "Fukushima";
                    /*  5881 */ break;
                case 9:
                    /*  5883 */ name = "Gifu";
                    /*  5884 */ break;
                case 10:
                    /*  5886 */ name = "Gumma";
                    /*  5887 */ break;
                case 11:
                    /*  5889 */ name = "Hiroshima";
                    /*  5890 */ break;
                case 12:
                    /*  5892 */ name = "Hokkaido";
                    /*  5893 */ break;
                case 13:
                    /*  5895 */ name = "Hyogo";
                    /*  5896 */ break;
                case 14:
                    /*  5898 */ name = "Ibaraki";
                    /*  5899 */ break;
                case 15:
                    /*  5901 */ name = "Ishikawa";
                    /*  5902 */ break;
                case 16:
                    /*  5904 */ name = "Iwate";
                    /*  5905 */ break;
                case 17:
                    /*  5907 */ name = "Kagawa";
                    /*  5908 */ break;
                case 18:
                    /*  5910 */ name = "Kagoshima";
                    /*  5911 */ break;
                case 19:
                    /*  5913 */ name = "Kanagawa";
                    /*  5914 */ break;
                case 20:
                    /*  5916 */ name = "Kochi";
                    /*  5917 */ break;
                case 21:
                    /*  5919 */ name = "Kumamoto";
                    /*  5920 */ break;
                case 22:
                    /*  5922 */ name = "Kyoto";
                    /*  5923 */ break;
                case 23:
                    /*  5925 */ name = "Mie";
                    /*  5926 */ break;
                case 24:
                    /*  5928 */ name = "Miyagi";
                    /*  5929 */ break;
                case 25:
                    /*  5931 */ name = "Miyazaki";
                    /*  5932 */ break;
                case 26:
                    /*  5934 */ name = "Nagano";
                    /*  5935 */ break;
                case 27:
                    /*  5937 */ name = "Nagasaki";
                    /*  5938 */ break;
                case 28:
                    /*  5940 */ name = "Nara";
                    /*  5941 */ break;
                case 29:
                    /*  5943 */ name = "Niigata";
                    /*  5944 */ break;
                case 30:
                    /*  5946 */ name = "Oita";
                    /*  5947 */ break;
                case 31:
                    /*  5949 */ name = "Okayama";
                    /*  5950 */ break;
                case 32:
                    /*  5952 */ name = "Osaka";
                    /*  5953 */ break;
                case 33:
                    /*  5955 */ name = "Saga";
                    /*  5956 */ break;
                case 34:
                    /*  5958 */ name = "Saitama";
                    /*  5959 */ break;
                case 35:
                    /*  5961 */ name = "Shiga";
                    /*  5962 */ break;
                case 36:
                    /*  5964 */ name = "Shimane";
                    /*  5965 */ break;
                case 37:
                    /*  5967 */ name = "Shizuoka";
                    /*  5968 */ break;
                case 38:
                    /*  5970 */ name = "Tochigi";
                    /*  5971 */ break;
                case 39:
                    /*  5973 */ name = "Tokushima";
                    /*  5974 */ break;
                case 40:
                    /*  5976 */ name = "Tokyo";
                    /*  5977 */ break;
                case 41:
                    /*  5979 */ name = "Tottori";
                    /*  5980 */ break;
                case 42:
                    /*  5982 */ name = "Toyama";
                    /*  5983 */ break;
                case 43:
                    /*  5985 */ name = "Wakayama";
                    /*  5986 */ break;
                case 44:
                    /*  5988 */ name = "Yamagata";
                    /*  5989 */ break;
                case 45:
                    /*  5991 */ name = "Yamaguchi";
                    /*  5992 */ break;
                case 46:
                    /*  5994 */ name = "Yamanashi";
                    /*  5995 */ break;
                case 47:
                    /*  5997 */ name = "Okinawa";
            }
        }

        /*  6001 */ if (country_code.equals("KE") == true) {
            /*  6002 */ switch (region_code2) {
                case 1:
                    /*  6004 */ name = "Central";
                    /*  6005 */ break;
                case 2:
                    /*  6007 */ name = "Coast";
                    /*  6008 */ break;
                case 3:
                    /*  6010 */ name = "Eastern";
                    /*  6011 */ break;
                case 5:
                    /*  6013 */ name = "Nairobi Area";
                    /*  6014 */ break;
                case 6:
                    /*  6016 */ name = "North-Eastern";
                    /*  6017 */ break;
                case 7:
                    /*  6019 */ name = "Nyanza";
                    /*  6020 */ break;
                case 8:
                    /*  6022 */ name = "Rift Valley";
                    /*  6023 */ break;
                case 9:
                    /*  6025 */ name = "Western";
                case 4:
            }
        }
        /*  6029 */ if (country_code.equals("KG") == true) {
            /*  6030 */ switch (region_code2) {
                case 1:
                    /*  6032 */ name = "Bishkek";
                    /*  6033 */ break;
                case 2:
                    /*  6035 */ name = "Chuy";
                    /*  6036 */ break;
                case 3:
                    /*  6038 */ name = "Jalal-Abad";
                    /*  6039 */ break;
                case 4:
                    /*  6041 */ name = "Naryn";
                    /*  6042 */ break;
                case 5:
                    /*  6044 */ name = "Osh";
                    /*  6045 */ break;
                case 6:
                    /*  6047 */ name = "Talas";
                    /*  6048 */ break;
                case 7:
                    /*  6050 */ name = "Ysyk-Kol";
                    /*  6051 */ break;
                case 8:
                    /*  6053 */ name = "Osh";
                    /*  6054 */ break;
                case 9:
                    /*  6056 */ name = "Batken";
            }
        }

        /*  6060 */ if (country_code.equals("KH") == true) /*  6061 */ {
            switch (region_code2) {
                case 1:
                    /*  6063 */ name = "Batdambang";
                    /*  6064 */ break;
                case 2:
                    /*  6066 */ name = "Kampong Cham";
                    /*  6067 */ break;
                case 3:
                    /*  6069 */ name = "Kampong Chhnang";
                    /*  6070 */ break;
                case 4:
                    /*  6072 */ name = "Kampong Speu";
                    /*  6073 */ break;
                case 5:
                    /*  6075 */ name = "Kampong Thum";
                    /*  6076 */ break;
                case 6:
                    /*  6078 */ name = "Kampot";
                    /*  6079 */ break;
                case 7:
                    /*  6081 */ name = "Kandal";
                    /*  6082 */ break;
                case 8:
                    /*  6084 */ name = "Koh Kong";
                    /*  6085 */ break;
                case 9:
                    /*  6087 */ name = "Kracheh";
                    /*  6088 */ break;
                case 10:
                    /*  6090 */ name = "Mondulkiri";
                    /*  6091 */ break;
                case 11:
                    /*  6093 */ name = "Phnum Penh";
                    /*  6094 */ break;
                case 12:
                    /*  6096 */ name = "Pursat";
                    /*  6097 */ break;
                case 13:
                    /*  6099 */ name = "Preah Vihear";
                    /*  6100 */ break;
                case 14:
                    /*  6102 */ name = "Prey Veng";
                    /*  6103 */ break;
                case 15:
                    /*  6105 */ name = "Ratanakiri Kiri";
                    /*  6106 */ break;
                case 16:
                    /*  6108 */ name = "Siem Reap";
                    /*  6109 */ break;
                case 17:
                    /*  6111 */ name = "Stung Treng";
                    /*  6112 */ break;
                case 18:
                    /*  6114 */ name = "Svay Rieng";
                    /*  6115 */ break;
                case 19:
                    /*  6117 */ name = "Takeo";
                    /*  6118 */ break;
                case 25:
                    /*  6120 */ name = "Banteay Meanchey";
                    /*  6121 */ break;
                case 29:
                    /*  6123 */ name = "Batdambang";
                    /*  6124 */ break;
                case 30:
                    /*  6126 */ name = "Pailin";
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 26:
                case 27:
                /*  6130 */ case 28:
            }
        }
        if (country_code.equals("KI") == true) {
            /*  6131 */ switch (region_code2) {
                case 1:
                    /*  6133 */ name = "Gilbert Islands";
                    /*  6134 */ break;
                case 2:
                    /*  6136 */ name = "Line Islands";
                    /*  6137 */ break;
                case 3:
                    /*  6139 */ name = "Phoenix Islands";
            }
        }

        /*  6143 */ if (country_code.equals("KM") == true) {
            /*  6144 */ switch (region_code2) {
                case 1:
                    /*  6146 */ name = "Anjouan";
                    /*  6147 */ break;
                case 2:
                    /*  6149 */ name = "Grande Comore";
                    /*  6150 */ break;
                case 3:
                    /*  6152 */ name = "Moheli";
            }
        }

        /*  6156 */ if (country_code.equals("KN") == true) {
            /*  6157 */ switch (region_code2) {
                case 1:
                    /*  6159 */ name = "Christ Church Nichola Town";
                    /*  6160 */ break;
                case 2:
                    /*  6162 */ name = "Saint Anne Sandy Point";
                    /*  6163 */ break;
                case 3:
                    /*  6165 */ name = "Saint George Basseterre";
                    /*  6166 */ break;
                case 4:
                    /*  6168 */ name = "Saint George Gingerland";
                    /*  6169 */ break;
                case 5:
                    /*  6171 */ name = "Saint James Windward";
                    /*  6172 */ break;
                case 6:
                    /*  6174 */ name = "Saint John Capisterre";
                    /*  6175 */ break;
                case 7:
                    /*  6177 */ name = "Saint John Figtree";
                    /*  6178 */ break;
                case 8:
                    /*  6180 */ name = "Saint Mary Cayon";
                    /*  6181 */ break;
                case 9:
                    /*  6183 */ name = "Saint Paul Capisterre";
                    /*  6184 */ break;
                case 10:
                    /*  6186 */ name = "Saint Paul Charlestown";
                    /*  6187 */ break;
                case 11:
                    /*  6189 */ name = "Saint Peter Basseterre";
                    /*  6190 */ break;
                case 12:
                    /*  6192 */ name = "Saint Thomas Lowland";
                    /*  6193 */ break;
                case 13:
                    /*  6195 */ name = "Saint Thomas Middle Island";
                    /*  6196 */ break;
                case 15:
                    /*  6198 */ name = "Trinity Palmetto Point";
                case 14:
            }
        }
        /*  6202 */ if (country_code.equals("KP") == true) /*  6203 */ {
            switch (region_code2) {
                case 1:
                    /*  6205 */ name = "Chagang-do";
                    /*  6206 */ break;
                case 3:
                    /*  6208 */ name = "Hamgyong-namdo";
                    /*  6209 */ break;
                case 6:
                    /*  6211 */ name = "Hwanghae-namdo";
                    /*  6212 */ break;
                case 7:
                    /*  6214 */ name = "Hwanghae-bukto";
                    /*  6215 */ break;
                case 8:
                    /*  6217 */ name = "Kaesong-si";
                    /*  6218 */ break;
                case 9:
                    /*  6220 */ name = "Kangwon-do";
                    /*  6221 */ break;
                case 11:
                    /*  6223 */ name = "P'yongan-bukto";
                    /*  6224 */ break;
                case 12:
                    /*  6226 */ name = "P'yongyang-si";
                    /*  6227 */ break;
                case 13:
                    /*  6229 */ name = "Yanggang-do";
                    /*  6230 */ break;
                case 14:
                    /*  6232 */ name = "Namp'o-si";
                    /*  6233 */ break;
                case 15:
                    /*  6235 */ name = "P'yongan-namdo";
                    /*  6236 */ break;
                case 17:
                    /*  6238 */ name = "Hamgyong-bukto";
                    /*  6239 */ break;
                case 18:
                    /*  6241 */ name = "Najin Sonbong-si";
                case 2:
                case 4:
                case 5:
                case 10:
                /*  6245 */ case 16:
            }
        }
        if (country_code.equals("KR") == true) /*  6246 */ {
            switch (region_code2) {
                case 1:
                    /*  6248 */ name = "Cheju-do";
                    /*  6249 */ break;
                case 3:
                    /*  6251 */ name = "Cholla-bukto";
                    /*  6252 */ break;
                case 5:
                    /*  6254 */ name = "Ch'ungch'ong-bukto";
                    /*  6255 */ break;
                case 6:
                    /*  6257 */ name = "Kangwon-do";
                    /*  6258 */ break;
                case 10:
                    /*  6260 */ name = "Pusan-jikhalsi";
                    /*  6261 */ break;
                case 11:
                    /*  6263 */ name = "Seoul-t'ukpyolsi";
                    /*  6264 */ break;
                case 12:
                    /*  6266 */ name = "Inch'on-jikhalsi";
                    /*  6267 */ break;
                case 13:
                    /*  6269 */ name = "Kyonggi-do";
                    /*  6270 */ break;
                case 14:
                    /*  6272 */ name = "Kyongsang-bukto";
                    /*  6273 */ break;
                case 15:
                    /*  6275 */ name = "Taegu-jikhalsi";
                    /*  6276 */ break;
                case 16:
                    /*  6278 */ name = "Cholla-namdo";
                    /*  6279 */ break;
                case 17:
                    /*  6281 */ name = "Ch'ungch'ong-namdo";
                    /*  6282 */ break;
                case 18:
                    /*  6284 */ name = "Kwangju-jikhalsi";
                    /*  6285 */ break;
                case 19:
                    /*  6287 */ name = "Taejon-jikhalsi";
                    /*  6288 */ break;
                case 20:
                    /*  6290 */ name = "Kyongsang-namdo";
                    /*  6291 */ break;
                case 21:
                    /*  6293 */ name = "Ulsan-gwangyoksi";
                case 2:
                case 4:
                case 7:
                case 8:
                /*  6297 */ case 9:
            }
        }
        if (country_code.equals("KW") == true) /*  6298 */ {
            switch (region_code2) {
                case 1:
                    /*  6300 */ name = "Al Ahmadi";
                    /*  6301 */ break;
                case 2:
                    /*  6303 */ name = "Al Kuwayt";
                    /*  6304 */ break;
                case 5:
                    /*  6306 */ name = "Al Jahra";
                    /*  6307 */ break;
                case 7:
                    /*  6309 */ name = "Al Farwaniyah";
                    /*  6310 */ break;
                case 8:
                    /*  6312 */ name = "Hawalli";
                    /*  6313 */ break;
                case 9:
                    /*  6315 */ name = "Mubarak al Kabir";
                case 3:
                case 4:
                case 6:
            }
        }
        /*  6319 */ if (country_code.equals("KY") == true) {
            /*  6320 */ switch (region_code2) {
                case 1:
                    /*  6322 */ name = "Creek";
                    /*  6323 */ break;
                case 2:
                    /*  6325 */ name = "Eastern";
                    /*  6326 */ break;
                case 3:
                    /*  6328 */ name = "Midland";
                    /*  6329 */ break;
                case 4:
                    /*  6331 */ name = "South Town";
                    /*  6332 */ break;
                case 5:
                    /*  6334 */ name = "Spot Bay";
                    /*  6335 */ break;
                case 6:
                    /*  6337 */ name = "Stake Bay";
                    /*  6338 */ break;
                case 7:
                    /*  6340 */ name = "West End";
                    /*  6341 */ break;
                case 8:
                    /*  6343 */ name = "Western";
            }
        }

        /*  6347 */ if (country_code.equals("KZ") == true) {
            /*  6348 */ switch (region_code2) {
                case 1:
                    /*  6350 */ name = "Almaty";
                    /*  6351 */ break;
                case 2:
                    /*  6353 */ name = "Almaty City";
                    /*  6354 */ break;
                case 3:
                    /*  6356 */ name = "Aqmola";
                    /*  6357 */ break;
                case 4:
                    /*  6359 */ name = "Aqtobe";
                    /*  6360 */ break;
                case 5:
                    /*  6362 */ name = "Astana";
                    /*  6363 */ break;
                case 6:
                    /*  6365 */ name = "Atyrau";
                    /*  6366 */ break;
                case 7:
                    /*  6368 */ name = "West Kazakhstan";
                    /*  6369 */ break;
                case 8:
                    /*  6371 */ name = "Bayqonyr";
                    /*  6372 */ break;
                case 9:
                    /*  6374 */ name = "Mangghystau";
                    /*  6375 */ break;
                case 10:
                    /*  6377 */ name = "South Kazakhstan";
                    /*  6378 */ break;
                case 11:
                    /*  6380 */ name = "Pavlodar";
                    /*  6381 */ break;
                case 12:
                    /*  6383 */ name = "Qaraghandy";
                    /*  6384 */ break;
                case 13:
                    /*  6386 */ name = "Qostanay";
                    /*  6387 */ break;
                case 14:
                    /*  6389 */ name = "Qyzylorda";
                    /*  6390 */ break;
                case 15:
                    /*  6392 */ name = "East Kazakhstan";
                    /*  6393 */ break;
                case 16:
                    /*  6395 */ name = "North Kazakhstan";
                    /*  6396 */ break;
                case 17:
                    /*  6398 */ name = "Zhambyl";
            }
        }

        /*  6402 */ if (country_code.equals("LA") == true) /*  6403 */ {
            switch (region_code2) {
                case 1:
                    /*  6405 */ name = "Attapu";
                    /*  6406 */ break;
                case 2:
                    /*  6408 */ name = "Champasak";
                    /*  6409 */ break;
                case 3:
                    /*  6411 */ name = "Houaphan";
                    /*  6412 */ break;
                case 4:
                    /*  6414 */ name = "Khammouan";
                    /*  6415 */ break;
                case 5:
                    /*  6417 */ name = "Louang Namtha";
                    /*  6418 */ break;
                case 7:
                    /*  6420 */ name = "Oudomxai";
                    /*  6421 */ break;
                case 8:
                    /*  6423 */ name = "Phongsali";
                    /*  6424 */ break;
                case 9:
                    /*  6426 */ name = "Saravan";
                    /*  6427 */ break;
                case 10:
                    /*  6429 */ name = "Savannakhet";
                    /*  6430 */ break;
                case 11:
                    /*  6432 */ name = "Vientiane";
                    /*  6433 */ break;
                case 13:
                    /*  6435 */ name = "Xaignabouri";
                    /*  6436 */ break;
                case 14:
                    /*  6438 */ name = "Xiangkhoang";
                    /*  6439 */ break;
                case 17:
                    /*  6441 */ name = "Louangphrabang";
                case 6:
                case 12:
                case 15:
                /*  6445 */ case 16:
            }
        }
        if (country_code.equals("LB") == true) {
            /*  6446 */ switch (region_code2) {
                case 1:
                    /*  6448 */ name = "Beqaa";
                    /*  6449 */ break;
                case 2:
                    /*  6451 */ name = "Al Janub";
                    /*  6452 */ break;
                case 3:
                    /*  6454 */ name = "Liban-Nord";
                    /*  6455 */ break;
                case 4:
                    /*  6457 */ name = "Beyrouth";
                    /*  6458 */ break;
                case 5:
                    /*  6460 */ name = "Mont-Liban";
                    /*  6461 */ break;
                case 6:
                    /*  6463 */ name = "Liban-Sud";
                    /*  6464 */ break;
                case 7:
                    /*  6466 */ name = "Nabatiye";
                    /*  6467 */ break;
                case 8:
                    /*  6469 */ name = "Beqaa";
                    /*  6470 */ break;
                case 9:
                    /*  6472 */ name = "Liban-Nord";
                    /*  6473 */ break;
                case 10:
                    /*  6475 */ name = "Aakk";
                    /*  6476 */ break;
                case 11:
                    /*  6478 */ name = "Baalbek-Hermel";
            }
        }

        /*  6482 */ if (country_code.equals("LC") == true) {
            /*  6483 */ switch (region_code2) {
                case 1:
                    /*  6485 */ name = "Anse-la-Raye";
                    /*  6486 */ break;
                case 2:
                    /*  6488 */ name = "Dauphin";
                    /*  6489 */ break;
                case 3:
                    /*  6491 */ name = "Castries";
                    /*  6492 */ break;
                case 4:
                    /*  6494 */ name = "Choiseul";
                    /*  6495 */ break;
                case 5:
                    /*  6497 */ name = "Dennery";
                    /*  6498 */ break;
                case 6:
                    /*  6500 */ name = "Gros-Islet";
                    /*  6501 */ break;
                case 7:
                    /*  6503 */ name = "Laborie";
                    /*  6504 */ break;
                case 8:
                    /*  6506 */ name = "Micoud";
                    /*  6507 */ break;
                case 9:
                    /*  6509 */ name = "Soufriere";
                    /*  6510 */ break;
                case 10:
                    /*  6512 */ name = "Vieux-Fort";
                    /*  6513 */ break;
                case 11:
                    /*  6515 */ name = "Praslin";
            }
        }

        /*  6519 */ if (country_code.equals("LI") == true) /*  6520 */ {
            switch (region_code2) {
                case 1:
                    /*  6522 */ name = "Balzers";
                    /*  6523 */ break;
                case 2:
                    /*  6525 */ name = "Eschen";
                    /*  6526 */ break;
                case 3:
                    /*  6528 */ name = "Gamprin";
                    /*  6529 */ break;
                case 4:
                    /*  6531 */ name = "Mauren";
                    /*  6532 */ break;
                case 5:
                    /*  6534 */ name = "Planken";
                    /*  6535 */ break;
                case 6:
                    /*  6537 */ name = "Ruggell";
                    /*  6538 */ break;
                case 7:
                    /*  6540 */ name = "Schaan";
                    /*  6541 */ break;
                case 8:
                    /*  6543 */ name = "Schellenberg";
                    /*  6544 */ break;
                case 9:
                    /*  6546 */ name = "Triesen";
                    /*  6547 */ break;
                case 10:
                    /*  6549 */ name = "Triesenberg";
                    /*  6550 */ break;
                case 11:
                    /*  6552 */ name = "Vaduz";
                    /*  6553 */ break;
                case 21:
                    /*  6555 */ name = "Gbarpolu";
                    /*  6556 */ break;
                case 22:
                    /*  6558 */ name = "River Gee";
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                /*  6562 */ case 20:
            }
        }
        if (country_code.equals("LK") == true) /*  6563 */ {
            switch (region_code2) {
                case 1:
                    /*  6565 */ name = "Amparai";
                    /*  6566 */ break;
                case 2:
                    /*  6568 */ name = "Anuradhapura";
                    /*  6569 */ break;
                case 3:
                    /*  6571 */ name = "Badulla";
                    /*  6572 */ break;
                case 4:
                    /*  6574 */ name = "Batticaloa";
                    /*  6575 */ break;
                case 6:
                    /*  6577 */ name = "Galle";
                    /*  6578 */ break;
                case 7:
                    /*  6580 */ name = "Hambantota";
                    /*  6581 */ break;
                case 9:
                    /*  6583 */ name = "Kalutara";
                    /*  6584 */ break;
                case 10:
                    /*  6586 */ name = "Kandy";
                    /*  6587 */ break;
                case 11:
                    /*  6589 */ name = "Kegalla";
                    /*  6590 */ break;
                case 12:
                    /*  6592 */ name = "Kurunegala";
                    /*  6593 */ break;
                case 14:
                    /*  6595 */ name = "Matale";
                    /*  6596 */ break;
                case 15:
                    /*  6598 */ name = "Matara";
                    /*  6599 */ break;
                case 16:
                    /*  6601 */ name = "Moneragala";
                    /*  6602 */ break;
                case 17:
                    /*  6604 */ name = "Nuwara Eliya";
                    /*  6605 */ break;
                case 18:
                    /*  6607 */ name = "Polonnaruwa";
                    /*  6608 */ break;
                case 19:
                    /*  6610 */ name = "Puttalam";
                    /*  6611 */ break;
                case 20:
                    /*  6613 */ name = "Ratnapura";
                    /*  6614 */ break;
                case 21:
                    /*  6616 */ name = "Trincomalee";
                    /*  6617 */ break;
                case 23:
                    /*  6619 */ name = "Colombo";
                    /*  6620 */ break;
                case 24:
                    /*  6622 */ name = "Gampaha";
                    /*  6623 */ break;
                case 25:
                    /*  6625 */ name = "Jaffna";
                    /*  6626 */ break;
                case 26:
                    /*  6628 */ name = "Mannar";
                    /*  6629 */ break;
                case 27:
                    /*  6631 */ name = "Mullaittivu";
                    /*  6632 */ break;
                case 28:
                    /*  6634 */ name = "Vavuniya";
                    /*  6635 */ break;
                case 29:
                    /*  6637 */ name = "Central";
                    /*  6638 */ break;
                case 30:
                    /*  6640 */ name = "North Central";
                    /*  6641 */ break;
                case 31:
                    /*  6643 */ name = "Northern";
                    /*  6644 */ break;
                case 32:
                    /*  6646 */ name = "North Western";
                    /*  6647 */ break;
                case 33:
                    /*  6649 */ name = "Sabaragamuwa";
                    /*  6650 */ break;
                case 34:
                    /*  6652 */ name = "Southern";
                    /*  6653 */ break;
                case 35:
                    /*  6655 */ name = "Uva";
                    /*  6656 */ break;
                case 36:
                    /*  6658 */ name = "Western";
                case 5:
                case 8:
                case 13:
                /*  6662 */ case 22:
            }
        }
        if (country_code.equals("LR") == true) /*  6663 */ {
            switch (region_code2) {
                case 1:
                    /*  6665 */ name = "Bong";
                    /*  6666 */ break;
                case 4:
                    /*  6668 */ name = "Grand Cape Mount";
                    /*  6669 */ break;
                case 5:
                    /*  6671 */ name = "Lofa";
                    /*  6672 */ break;
                case 6:
                    /*  6674 */ name = "Maryland";
                    /*  6675 */ break;
                case 7:
                    /*  6677 */ name = "Monrovia";
                    /*  6678 */ break;
                case 9:
                    /*  6680 */ name = "Nimba";
                    /*  6681 */ break;
                case 10:
                    /*  6683 */ name = "Sino";
                    /*  6684 */ break;
                case 11:
                    /*  6686 */ name = "Grand Bassa";
                    /*  6687 */ break;
                case 12:
                    /*  6689 */ name = "Grand Cape Mount";
                    /*  6690 */ break;
                case 13:
                    /*  6692 */ name = "Maryland";
                    /*  6693 */ break;
                case 14:
                    /*  6695 */ name = "Montserrado";
                    /*  6696 */ break;
                case 17:
                    /*  6698 */ name = "Margibi";
                    /*  6699 */ break;
                case 18:
                    /*  6701 */ name = "River Cess";
                    /*  6702 */ break;
                case 19:
                    /*  6704 */ name = "Grand Gedeh";
                    /*  6705 */ break;
                case 20:
                    /*  6707 */ name = "Lofa";
                    /*  6708 */ break;
                case 21:
                    /*  6710 */ name = "Gbarpolu";
                    /*  6711 */ break;
                case 22:
                    /*  6713 */ name = "River Gee";
                case 2:
                case 3:
                case 8:
                case 15:
                /*  6717 */ case 16:
            }
        }
        if (country_code.equals("LS") == true) {
            /*  6718 */ switch (region_code2) {
                case 10:
                    /*  6720 */ name = "Berea";
                    /*  6721 */ break;
                case 11:
                    /*  6723 */ name = "Butha-Buthe";
                    /*  6724 */ break;
                case 12:
                    /*  6726 */ name = "Leribe";
                    /*  6727 */ break;
                case 13:
                    /*  6729 */ name = "Mafeteng";
                    /*  6730 */ break;
                case 14:
                    /*  6732 */ name = "Maseru";
                    /*  6733 */ break;
                case 15:
                    /*  6735 */ name = "Mohales Hoek";
                    /*  6736 */ break;
                case 16:
                    /*  6738 */ name = "Mokhotlong";
                    /*  6739 */ break;
                case 17:
                    /*  6741 */ name = "Qachas Nek";
                    /*  6742 */ break;
                case 18:
                    /*  6744 */ name = "Quthing";
                    /*  6745 */ break;
                case 19:
                    /*  6747 */ name = "Thaba-Tseka";
            }
        }

        /*  6751 */ if (country_code.equals("LT") == true) {
            /*  6752 */ switch (region_code2) {
                case 56:
                    /*  6754 */ name = "Alytaus Apskritis";
                    /*  6755 */ break;
                case 57:
                    /*  6757 */ name = "Kauno Apskritis";
                    /*  6758 */ break;
                case 58:
                    /*  6760 */ name = "Klaipedos Apskritis";
                    /*  6761 */ break;
                case 59:
                    /*  6763 */ name = "Marijampoles Apskritis";
                    /*  6764 */ break;
                case 60:
                    /*  6766 */ name = "Panevezio Apskritis";
                    /*  6767 */ break;
                case 61:
                    /*  6769 */ name = "Siauliu Apskritis";
                    /*  6770 */ break;
                case 62:
                    /*  6772 */ name = "Taurages Apskritis";
                    /*  6773 */ break;
                case 63:
                    /*  6775 */ name = "Telsiu Apskritis";
                    /*  6776 */ break;
                case 64:
                    /*  6778 */ name = "Utenos Apskritis";
                    /*  6779 */ break;
                case 65:
                    /*  6781 */ name = "Vilniaus Apskritis";
            }
        }

        /*  6785 */ if (country_code.equals("LU") == true) {
            /*  6786 */ switch (region_code2) {
                case 1:
                    /*  6788 */ name = "Diekirch";
                    /*  6789 */ break;
                case 2:
                    /*  6791 */ name = "Grevenmacher";
                    /*  6792 */ break;
                case 3:
                    /*  6794 */ name = "Luxembourg";
            }
        }

        /*  6798 */ if (country_code.equals("LV") == true) {
            /*  6799 */ switch (region_code2) {
                case 1:
                    /*  6801 */ name = "Aizkraukles";
                    /*  6802 */ break;
                case 2:
                    /*  6804 */ name = "Aluksnes";
                    /*  6805 */ break;
                case 3:
                    /*  6807 */ name = "Balvu";
                    /*  6808 */ break;
                case 4:
                    /*  6810 */ name = "Bauskas";
                    /*  6811 */ break;
                case 5:
                    /*  6813 */ name = "Cesu";
                    /*  6814 */ break;
                case 6:
                    /*  6816 */ name = "Daugavpils";
                    /*  6817 */ break;
                case 7:
                    /*  6819 */ name = "Daugavpils";
                    /*  6820 */ break;
                case 8:
                    /*  6822 */ name = "Dobeles";
                    /*  6823 */ break;
                case 9:
                    /*  6825 */ name = "Gulbenes";
                    /*  6826 */ break;
                case 10:
                    /*  6828 */ name = "Jekabpils";
                    /*  6829 */ break;
                case 11:
                    /*  6831 */ name = "Jelgava";
                    /*  6832 */ break;
                case 12:
                    /*  6834 */ name = "Jelgavas";
                    /*  6835 */ break;
                case 13:
                    /*  6837 */ name = "Jurmala";
                    /*  6838 */ break;
                case 14:
                    /*  6840 */ name = "Kraslavas";
                    /*  6841 */ break;
                case 15:
                    /*  6843 */ name = "Kuldigas";
                    /*  6844 */ break;
                case 16:
                    /*  6846 */ name = "Liepaja";
                    /*  6847 */ break;
                case 17:
                    /*  6849 */ name = "Liepajas";
                    /*  6850 */ break;
                case 18:
                    /*  6852 */ name = "Limbazu";
                    /*  6853 */ break;
                case 19:
                    /*  6855 */ name = "Ludzas";
                    /*  6856 */ break;
                case 20:
                    /*  6858 */ name = "Madonas";
                    /*  6859 */ break;
                case 21:
                    /*  6861 */ name = "Ogres";
                    /*  6862 */ break;
                case 22:
                    /*  6864 */ name = "Preilu";
                    /*  6865 */ break;
                case 23:
                    /*  6867 */ name = "Rezekne";
                    /*  6868 */ break;
                case 24:
                    /*  6870 */ name = "Rezeknes";
                    /*  6871 */ break;
                case 25:
                    /*  6873 */ name = "Riga";
                    /*  6874 */ break;
                case 26:
                    /*  6876 */ name = "Rigas";
                    /*  6877 */ break;
                case 27:
                    /*  6879 */ name = "Saldus";
                    /*  6880 */ break;
                case 28:
                    /*  6882 */ name = "Talsu";
                    /*  6883 */ break;
                case 29:
                    /*  6885 */ name = "Tukuma";
                    /*  6886 */ break;
                case 30:
                    /*  6888 */ name = "Valkas";
                    /*  6889 */ break;
                case 31:
                    /*  6891 */ name = "Valmieras";
                    /*  6892 */ break;
                case 32:
                    /*  6894 */ name = "Ventspils";
                    /*  6895 */ break;
                case 33:
                    /*  6897 */ name = "Ventspils";
            }
        }

        /*  6901 */ if (country_code.equals("LY") == true) /*  6902 */ {
            switch (region_code2) {
                case 3:
                    /*  6904 */ name = "Al Aziziyah";
                    /*  6905 */ break;
                case 5:
                    /*  6907 */ name = "Al Jufrah";
                    /*  6908 */ break;
                case 8:
                    /*  6910 */ name = "Al Kufrah";
                    /*  6911 */ break;
                case 13:
                    /*  6913 */ name = "Ash Shati'";
                    /*  6914 */ break;
                case 30:
                    /*  6916 */ name = "Murzuq";
                    /*  6917 */ break;
                case 34:
                    /*  6919 */ name = "Sabha";
                    /*  6920 */ break;
                case 41:
                    /*  6922 */ name = "Tarhunah";
                    /*  6923 */ break;
                case 42:
                    /*  6925 */ name = "Tubruq";
                    /*  6926 */ break;
                case 45:
                    /*  6928 */ name = "Zlitan";
                    /*  6929 */ break;
                case 47:
                    /*  6931 */ name = "Ajdabiya";
                    /*  6932 */ break;
                case 48:
                    /*  6934 */ name = "Al Fatih";
                    /*  6935 */ break;
                case 49:
                    /*  6937 */ name = "Al Jabal al Akhdar";
                    /*  6938 */ break;
                case 50:
                    /*  6940 */ name = "Al Khums";
                    /*  6941 */ break;
                case 51:
                    /*  6943 */ name = "An Nuqat al Khams";
                    /*  6944 */ break;
                case 52:
                    /*  6946 */ name = "Awbari";
                    /*  6947 */ break;
                case 53:
                    /*  6949 */ name = "Az Zawiyah";
                    /*  6950 */ break;
                case 54:
                    /*  6952 */ name = "Banghazi";
                    /*  6953 */ break;
                case 55:
                    /*  6955 */ name = "Darnah";
                    /*  6956 */ break;
                case 56:
                    /*  6958 */ name = "Ghadamis";
                    /*  6959 */ break;
                case 57:
                    /*  6961 */ name = "Gharyan";
                    /*  6962 */ break;
                case 58:
                    /*  6964 */ name = "Misratah";
                    /*  6965 */ break;
                case 59:
                    /*  6967 */ name = "Sawfajjin";
                    /*  6968 */ break;
                case 60:
                    /*  6970 */ name = "Surt";
                    /*  6971 */ break;
                case 61:
                    /*  6973 */ name = "Tarabulus";
                    /*  6974 */ break;
                case 62:
                    /*  6976 */ name = "Yafran";
                case 4:
                case 6:
                case 7:
                case 9:
                case 10:
                case 11:
                case 12:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 31:
                case 32:
                case 33:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 43:
                case 44:
                /*  6980 */ case 46:
            }
        }
        if (country_code.equals("MA") == true) {
            /*  6981 */ switch (region_code2) {
                case 45:
                    /*  6983 */ name = "Grand Casablanca";
                    /*  6984 */ break;
                case 46:
                    /*  6986 */ name = "Fes-Boulemane";
                    /*  6987 */ break;
                case 47:
                    /*  6989 */ name = "Marrakech-Tensift-Al Haouz";
                    /*  6990 */ break;
                case 48:
                    /*  6992 */ name = "Meknes-Tafilalet";
                    /*  6993 */ break;
                case 49:
                    /*  6995 */ name = "Rabat-Sale-Zemmour-Zaer";
                    /*  6996 */ break;
                case 50:
                    /*  6998 */ name = "Chaouia-Ouardigha";
                    /*  6999 */ break;
                case 51:
                    /*  7001 */ name = "Doukkala-Abda";
                    /*  7002 */ break;
                case 52:
                    /*  7004 */ name = "Gharb-Chrarda-Beni Hssen";
                    /*  7005 */ break;
                case 53:
                    /*  7007 */ name = "Guelmim-Es Smara";
                    /*  7008 */ break;
                case 54:
                    /*  7010 */ name = "Oriental";
                    /*  7011 */ break;
                case 55:
                    /*  7013 */ name = "Souss-Massa-Dr";
                    /*  7014 */ break;
                case 56:
                    /*  7016 */ name = "Tadla-Azilal";
                    /*  7017 */ break;
                case 57:
                    /*  7019 */ name = "Tanger-Tetouan";
                    /*  7020 */ break;
                case 58:
                    /*  7022 */ name = "Taza-Al Hoceima-Taounate";
                    /*  7023 */ break;
                case 59:
                    /*  7025 */ name = "La";
            }
        }

        /*  7029 */ if (country_code.equals("MC") == true) {
            /*  7030 */ switch (region_code2) {
                case 1:
                    /*  7032 */ name = "La Condamine";
                    /*  7033 */ break;
                case 2:
                    /*  7035 */ name = "Monaco";
                    /*  7036 */ break;
                case 3:
                    /*  7038 */ name = "Monte-Carlo";
            }
        }

        /*  7042 */ if (country_code.equals("MD") == true) /*  7043 */ {
            switch (region_code2) {
                case 51:
                    /*  7045 */ name = "Gagauzia";
                    /*  7046 */ break;
                case 57:
                    /*  7048 */ name = "Chisinau";
                    /*  7049 */ break;
                case 58:
                    /*  7051 */ name = "Stinga Nistrului";
                    /*  7052 */ break;
                case 59:
                    /*  7054 */ name = "Anenii Noi";
                    /*  7055 */ break;
                case 60:
                    /*  7057 */ name = "Balti";
                    /*  7058 */ break;
                case 61:
                    /*  7060 */ name = "Basarabeasca";
                    /*  7061 */ break;
                case 62:
                    /*  7063 */ name = "Bender";
                    /*  7064 */ break;
                case 63:
                    /*  7066 */ name = "Briceni";
                    /*  7067 */ break;
                case 64:
                    /*  7069 */ name = "Cahul";
                    /*  7070 */ break;
                case 65:
                    /*  7072 */ name = "Cantemir";
                    /*  7073 */ break;
                case 66:
                    /*  7075 */ name = "Calarasi";
                    /*  7076 */ break;
                case 67:
                    /*  7078 */ name = "Causeni";
                    /*  7079 */ break;
                case 68:
                    /*  7081 */ name = "Cimislia";
                    /*  7082 */ break;
                case 69:
                    /*  7084 */ name = "Criuleni";
                    /*  7085 */ break;
                case 70:
                    /*  7087 */ name = "Donduseni";
                    /*  7088 */ break;
                case 71:
                    /*  7090 */ name = "Drochia";
                    /*  7091 */ break;
                case 72:
                    /*  7093 */ name = "Dubasari";
                    /*  7094 */ break;
                case 73:
                    /*  7096 */ name = "Edinet";
                    /*  7097 */ break;
                case 74:
                    /*  7099 */ name = "Falesti";
                    /*  7100 */ break;
                case 75:
                    /*  7102 */ name = "Floresti";
                    /*  7103 */ break;
                case 76:
                    /*  7105 */ name = "Glodeni";
                    /*  7106 */ break;
                case 77:
                    /*  7108 */ name = "Hincesti";
                    /*  7109 */ break;
                case 78:
                    /*  7111 */ name = "Ialoveni";
                    /*  7112 */ break;
                case 79:
                    /*  7114 */ name = "Leova";
                    /*  7115 */ break;
                case 80:
                    /*  7117 */ name = "Nisporeni";
                    /*  7118 */ break;
                case 81:
                    /*  7120 */ name = "Ocnita";
                    /*  7121 */ break;
                case 82:
                    /*  7123 */ name = "Orhei";
                    /*  7124 */ break;
                case 83:
                    /*  7126 */ name = "Rezina";
                    /*  7127 */ break;
                case 84:
                    /*  7129 */ name = "Riscani";
                    /*  7130 */ break;
                case 85:
                    /*  7132 */ name = "Singerei";
                    /*  7133 */ break;
                case 86:
                    /*  7135 */ name = "Soldanesti";
                    /*  7136 */ break;
                case 87:
                    /*  7138 */ name = "Soroca";
                    /*  7139 */ break;
                case 88:
                    /*  7141 */ name = "Stefan-Voda";
                    /*  7142 */ break;
                case 89:
                    /*  7144 */ name = "Straseni";
                    /*  7145 */ break;
                case 90:
                    /*  7147 */ name = "Taraclia";
                    /*  7148 */ break;
                case 91:
                    /*  7150 */ name = "Telenesti";
                    /*  7151 */ break;
                case 92:
                    /*  7153 */ name = "Ungheni";
                case 52:
                case 53:
                case 54:
                case 55:
                /*  7157 */ case 56:
            }
        }
        if (country_code.equals("MG") == true) {
            /*  7158 */ switch (region_code2) {
                case 1:
                    /*  7160 */ name = "Antsiranana";
                    /*  7161 */ break;
                case 2:
                    /*  7163 */ name = "Fianarantsoa";
                    /*  7164 */ break;
                case 3:
                    /*  7166 */ name = "Mahajanga";
                    /*  7167 */ break;
                case 4:
                    /*  7169 */ name = "Toamasina";
                    /*  7170 */ break;
                case 5:
                    /*  7172 */ name = "Antananarivo";
                    /*  7173 */ break;
                case 6:
                    /*  7175 */ name = "Toliara";
            }
        }

        /*  7179 */ if (country_code.equals("MK") == true) {
            /*  7180 */ switch (region_code2) {
                case 1:
                    /*  7182 */ name = "Aracinovo";
                    /*  7183 */ break;
                case 2:
                    /*  7185 */ name = "Bac";
                    /*  7186 */ break;
                case 3:
                    /*  7188 */ name = "Belcista";
                    /*  7189 */ break;
                case 4:
                    /*  7191 */ name = "Berovo";
                    /*  7192 */ break;
                case 5:
                    /*  7194 */ name = "Bistrica";
                    /*  7195 */ break;
                case 6:
                    /*  7197 */ name = "Bitola";
                    /*  7198 */ break;
                case 7:
                    /*  7200 */ name = "Blatec";
                    /*  7201 */ break;
                case 8:
                    /*  7203 */ name = "Bogdanci";
                    /*  7204 */ break;
                case 9:
                    /*  7206 */ name = "Bogomila";
                    /*  7207 */ break;
                case 10:
                    /*  7209 */ name = "Bogovinje";
                    /*  7210 */ break;
                case 11:
                    /*  7212 */ name = "Bosilovo";
                    /*  7213 */ break;
                case 12:
                    /*  7215 */ name = "Brvenica";
                    /*  7216 */ break;
                case 13:
                    /*  7218 */ name = "Cair";
                    /*  7219 */ break;
                case 14:
                    /*  7221 */ name = "Capari";
                    /*  7222 */ break;
                case 15:
                    /*  7224 */ name = "Caska";
                    /*  7225 */ break;
                case 16:
                    /*  7227 */ name = "Cegrane";
                    /*  7228 */ break;
                case 17:
                    /*  7230 */ name = "Centar";
                    /*  7231 */ break;
                case 18:
                    /*  7233 */ name = "Centar Zupa";
                    /*  7234 */ break;
                case 19:
                    /*  7236 */ name = "Cesinovo";
                    /*  7237 */ break;
                case 20:
                    /*  7239 */ name = "Cucer-Sandevo";
                    /*  7240 */ break;
                case 21:
                    /*  7242 */ name = "Debar";
                    /*  7243 */ break;
                case 22:
                    /*  7245 */ name = "Delcevo";
                    /*  7246 */ break;
                case 23:
                    /*  7248 */ name = "Delogozdi";
                    /*  7249 */ break;
                case 24:
                    /*  7251 */ name = "Demir Hisar";
                    /*  7252 */ break;
                case 25:
                    /*  7254 */ name = "Demir Kapija";
                    /*  7255 */ break;
                case 26:
                    /*  7257 */ name = "Dobrusevo";
                    /*  7258 */ break;
                case 27:
                    /*  7260 */ name = "Dolna Banjica";
                    /*  7261 */ break;
                case 28:
                    /*  7263 */ name = "Dolneni";
                    /*  7264 */ break;
                case 29:
                    /*  7266 */ name = "Dorce Petrov";
                    /*  7267 */ break;
                case 30:
                    /*  7269 */ name = "Drugovo";
                    /*  7270 */ break;
                case 31:
                    /*  7272 */ name = "Dzepciste";
                    /*  7273 */ break;
                case 32:
                    /*  7275 */ name = "Gazi Baba";
                    /*  7276 */ break;
                case 33:
                    /*  7278 */ name = "Gevgelija";
                    /*  7279 */ break;
                case 34:
                    /*  7281 */ name = "Gostivar";
                    /*  7282 */ break;
                case 35:
                    /*  7284 */ name = "Gradsko";
                    /*  7285 */ break;
                case 36:
                    /*  7287 */ name = "Ilinden";
                    /*  7288 */ break;
                case 37:
                    /*  7290 */ name = "Izvor";
                    /*  7291 */ break;
                case 38:
                    /*  7293 */ name = "Jegunovce";
                    /*  7294 */ break;
                case 39:
                    /*  7296 */ name = "Kamenjane";
                    /*  7297 */ break;
                case 40:
                    /*  7299 */ name = "Karbinci";
                    /*  7300 */ break;
                case 41:
                    /*  7302 */ name = "Karpos";
                    /*  7303 */ break;
                case 42:
                    /*  7305 */ name = "Kavadarci";
                    /*  7306 */ break;
                case 43:
                    /*  7308 */ name = "Kicevo";
                    /*  7309 */ break;
                case 44:
                    /*  7311 */ name = "Kisela Voda";
                    /*  7312 */ break;
                case 45:
                    /*  7314 */ name = "Klecevce";
                    /*  7315 */ break;
                case 46:
                    /*  7317 */ name = "Kocani";
                    /*  7318 */ break;
                case 47:
                    /*  7320 */ name = "Konce";
                    /*  7321 */ break;
                case 48:
                    /*  7323 */ name = "Kondovo";
                    /*  7324 */ break;
                case 49:
                    /*  7326 */ name = "Konopiste";
                    /*  7327 */ break;
                case 50:
                    /*  7329 */ name = "Kosel";
                    /*  7330 */ break;
                case 51:
                    /*  7332 */ name = "Kratovo";
                    /*  7333 */ break;
                case 52:
                    /*  7335 */ name = "Kriva Palanka";
                    /*  7336 */ break;
                case 53:
                    /*  7338 */ name = "Krivogastani";
                    /*  7339 */ break;
                case 54:
                    /*  7341 */ name = "Krusevo";
                    /*  7342 */ break;
                case 55:
                    /*  7344 */ name = "Kuklis";
                    /*  7345 */ break;
                case 56:
                    /*  7347 */ name = "Kukurecani";
                    /*  7348 */ break;
                case 57:
                    /*  7350 */ name = "Kumanovo";
                    /*  7351 */ break;
                case 58:
                    /*  7353 */ name = "Labunista";
                    /*  7354 */ break;
                case 59:
                    /*  7356 */ name = "Lipkovo";
                    /*  7357 */ break;
                case 60:
                    /*  7359 */ name = "Lozovo";
                    /*  7360 */ break;
                case 61:
                    /*  7362 */ name = "Lukovo";
                    /*  7363 */ break;
                case 62:
                    /*  7365 */ name = "Makedonska Kamenica";
                    /*  7366 */ break;
                case 63:
                    /*  7368 */ name = "Makedonski Brod";
                    /*  7369 */ break;
                case 64:
                    /*  7371 */ name = "Mavrovi Anovi";
                    /*  7372 */ break;
                case 65:
                    /*  7374 */ name = "Meseista";
                    /*  7375 */ break;
                case 66:
                    /*  7377 */ name = "Miravci";
                    /*  7378 */ break;
                case 67:
                    /*  7380 */ name = "Mogila";
                    /*  7381 */ break;
                case 68:
                    /*  7383 */ name = "Murtino";
                    /*  7384 */ break;
                case 69:
                    /*  7386 */ name = "Negotino";
                    /*  7387 */ break;
                case 70:
                    /*  7389 */ name = "Negotino-Polosko";
                    /*  7390 */ break;
                case 71:
                    /*  7392 */ name = "Novaci";
                    /*  7393 */ break;
                case 72:
                    /*  7395 */ name = "Novo Selo";
                    /*  7396 */ break;
                case 73:
                    /*  7398 */ name = "Oblesevo";
                    /*  7399 */ break;
                case 74:
                    /*  7401 */ name = "Ohrid";
                    /*  7402 */ break;
                case 75:
                    /*  7404 */ name = "Orasac";
                    /*  7405 */ break;
                case 76:
                    /*  7407 */ name = "Orizari";
                    /*  7408 */ break;
                case 77:
                    /*  7410 */ name = "Oslomej";
                    /*  7411 */ break;
                case 78:
                    /*  7413 */ name = "Pehcevo";
                    /*  7414 */ break;
                case 79:
                    /*  7416 */ name = "Petrovec";
                    /*  7417 */ break;
                case 80:
                    /*  7419 */ name = "Plasnica";
                    /*  7420 */ break;
                case 81:
                    /*  7422 */ name = "Podares";
                    /*  7423 */ break;
                case 82:
                    /*  7425 */ name = "Prilep";
                    /*  7426 */ break;
                case 83:
                    /*  7428 */ name = "Probistip";
                    /*  7429 */ break;
                case 84:
                    /*  7431 */ name = "Radovis";
                    /*  7432 */ break;
                case 85:
                    /*  7434 */ name = "Rankovce";
                    /*  7435 */ break;
                case 86:
                    /*  7437 */ name = "Resen";
                    /*  7438 */ break;
                case 87:
                    /*  7440 */ name = "Rosoman";
                    /*  7441 */ break;
                case 88:
                    /*  7443 */ name = "Rostusa";
                    /*  7444 */ break;
                case 89:
                    /*  7446 */ name = "Samokov";
                    /*  7447 */ break;
                case 90:
                    /*  7449 */ name = "Saraj";
                    /*  7450 */ break;
                case 91:
                    /*  7452 */ name = "Sipkovica";
                    /*  7453 */ break;
                case 92:
                    /*  7455 */ name = "Sopiste";
                    /*  7456 */ break;
                case 93:
                    /*  7458 */ name = "Sopotnica";
                    /*  7459 */ break;
                case 94:
                    /*  7461 */ name = "Srbinovo";
                    /*  7462 */ break;
                case 95:
                    /*  7464 */ name = "Staravina";
                    /*  7465 */ break;
                case 96:
                    /*  7467 */ name = "Star Dojran";
                    /*  7468 */ break;
                case 97:
                    /*  7470 */ name = "Staro Nagoricane";
                    /*  7471 */ break;
                case 98:
                    /*  7473 */ name = "Stip";
                    /*  7474 */ break;
                case 99:
                    /*  7476 */ name = "Struga";
                    /*  7477 */ break;
                case 832:
                    /*  7479 */ name = "Strumica";
                    /*  7480 */ break;
                case 833:
                    /*  7482 */ name = "Studenicani";
                    /*  7483 */ break;
                case 834:
                    /*  7485 */ name = "Suto Orizari";
                    /*  7486 */ break;
                case 835:
                    /*  7488 */ name = "Sveti Nikole";
                    /*  7489 */ break;
                case 836:
                    /*  7491 */ name = "Tearce";
                    /*  7492 */ break;
                case 837:
                    /*  7494 */ name = "Tetovo";
                    /*  7495 */ break;
                case 838:
                    /*  7497 */ name = "Topolcani";
                    /*  7498 */ break;
                case 839:
                    /*  7500 */ name = "Valandovo";
                    /*  7501 */ break;
                case 840:
                    /*  7503 */ name = "Vasilevo";
                    /*  7504 */ break;
                case 875:
                    /*  7506 */ name = "Veles";
                    /*  7507 */ break;
                case 876:
                    /*  7509 */ name = "Velesta";
                    /*  7510 */ break;
                case 877:
                    /*  7512 */ name = "Vevcani";
                    /*  7513 */ break;
                case 878:
                    /*  7515 */ name = "Vinica";
                    /*  7516 */ break;
                case 879:
                    /*  7518 */ name = "Vitoliste";
                    /*  7519 */ break;
                case 880:
                    /*  7521 */ name = "Vranestica";
                    /*  7522 */ break;
                case 881:
                    /*  7524 */ name = "Vrapciste";
                    /*  7525 */ break;
                case 882:
                    /*  7527 */ name = "Vratnica";
                    /*  7528 */ break;
                case 883:
                    /*  7530 */ name = "Vrutok";
                    /*  7531 */ break;
                case 918:
                    /*  7533 */ name = "Zajas";
                    /*  7534 */ break;
                case 919:
                    /*  7536 */ name = "Zelenikovo";
                    /*  7537 */ break;
                case 920:
                    /*  7539 */ name = "Zelino";
                    /*  7540 */ break;
                case 921:
                    /*  7542 */ name = "Zitose";
                    /*  7543 */ break;
                case 922:
                    /*  7545 */ name = "Zletovo";
                    /*  7546 */ break;
                case 923:
                    /*  7548 */ name = "Zrnovci";
            }
        }

        /*  7552 */ if (country_code.equals("ML") == true) {
            /*  7553 */ switch (region_code2) {
                case 1:
                    /*  7555 */ name = "Bamako";
                    /*  7556 */ break;
                case 3:
                    /*  7558 */ name = "Kayes";
                    /*  7559 */ break;
                case 4:
                    /*  7561 */ name = "Mopti";
                    /*  7562 */ break;
                case 5:
                    /*  7564 */ name = "Segou";
                    /*  7565 */ break;
                case 6:
                    /*  7567 */ name = "Sikasso";
                    /*  7568 */ break;
                case 7:
                    /*  7570 */ name = "Koulikoro";
                    /*  7571 */ break;
                case 8:
                    /*  7573 */ name = "Tombouctou";
                    /*  7574 */ break;
                case 9:
                    /*  7576 */ name = "Gao";
                    /*  7577 */ break;
                case 10:
                    /*  7579 */ name = "Kidal";
                case 2:
            }
        }
        /*  7583 */ if (country_code.equals("MM") == true) /*  7584 */ {
            switch (region_code2) {
                case 1:
                    /*  7586 */ name = "Rakhine State";
                    /*  7587 */ break;
                case 2:
                    /*  7589 */ name = "Chin State";
                    /*  7590 */ break;
                case 3:
                    /*  7592 */ name = "Irrawaddy";
                    /*  7593 */ break;
                case 4:
                    /*  7595 */ name = "Kachin State";
                    /*  7596 */ break;
                case 5:
                    /*  7598 */ name = "Karan State";
                    /*  7599 */ break;
                case 6:
                    /*  7601 */ name = "Kayah State";
                    /*  7602 */ break;
                case 7:
                    /*  7604 */ name = "Magwe";
                    /*  7605 */ break;
                case 8:
                    /*  7607 */ name = "Mandalay";
                    /*  7608 */ break;
                case 9:
                    /*  7610 */ name = "Pegu";
                    /*  7611 */ break;
                case 10:
                    /*  7613 */ name = "Sagaing";
                    /*  7614 */ break;
                case 11:
                    /*  7616 */ name = "Shan State";
                    /*  7617 */ break;
                case 12:
                    /*  7619 */ name = "Tenasserim";
                    /*  7620 */ break;
                case 13:
                    /*  7622 */ name = "Mon State";
                    /*  7623 */ break;
                case 14:
                    /*  7625 */ name = "Rangoon";
                    /*  7626 */ break;
                case 17:
                    /*  7628 */ name = "Yangon";
                case 15:
                case 16:
            }
        }
        /*  7632 */ if (country_code.equals("MN") == true) {
            /*  7633 */ switch (region_code2) {
                case 1:
                    /*  7635 */ name = "Arhangay";
                    /*  7636 */ break;
                case 2:
                    /*  7638 */ name = "Bayanhongor";
                    /*  7639 */ break;
                case 3:
                    /*  7641 */ name = "Bayan-Olgiy";
                    /*  7642 */ break;
                case 5:
                    /*  7644 */ name = "Darhan";
                    /*  7645 */ break;
                case 6:
                    /*  7647 */ name = "Dornod";
                    /*  7648 */ break;
                case 7:
                    /*  7650 */ name = "Dornogovi";
                    /*  7651 */ break;
                case 8:
                    /*  7653 */ name = "Dundgovi";
                    /*  7654 */ break;
                case 9:
                    /*  7656 */ name = "Dzavhan";
                    /*  7657 */ break;
                case 10:
                    /*  7659 */ name = "Govi-Altay";
                    /*  7660 */ break;
                case 11:
                    /*  7662 */ name = "Hentiy";
                    /*  7663 */ break;
                case 12:
                    /*  7665 */ name = "Hovd";
                    /*  7666 */ break;
                case 13:
                    /*  7668 */ name = "Hovsgol";
                    /*  7669 */ break;
                case 14:
                    /*  7671 */ name = "Omnogovi";
                    /*  7672 */ break;
                case 15:
                    /*  7674 */ name = "Ovorhangay";
                    /*  7675 */ break;
                case 16:
                    /*  7677 */ name = "Selenge";
                    /*  7678 */ break;
                case 17:
                    /*  7680 */ name = "Suhbaatar";
                    /*  7681 */ break;
                case 18:
                    /*  7683 */ name = "Tov";
                    /*  7684 */ break;
                case 19:
                    /*  7686 */ name = "Uvs";
                    /*  7687 */ break;
                case 20:
                    /*  7689 */ name = "Ulaanbaatar";
                    /*  7690 */ break;
                case 21:
                    /*  7692 */ name = "Bulgan";
                    /*  7693 */ break;
                case 22:
                    /*  7695 */ name = "Erdenet";
                    /*  7696 */ break;
                case 23:
                    /*  7698 */ name = "Darhan-Uul";
                    /*  7699 */ break;
                case 24:
                    /*  7701 */ name = "Govisumber";
                    /*  7702 */ break;
                case 25:
                    /*  7704 */ name = "Orhon";
                case 4:
            }
        }
        /*  7708 */ if (country_code.equals("MO") == true) {
            /*  7709 */ switch (region_code2) {
                case 1:
                    /*  7711 */ name = "Ilhas";
                    /*  7712 */ break;
                case 2:
                    /*  7714 */ name = "Macau";
            }
        }

        /*  7718 */ if (country_code.equals("MR") == true) {
            /*  7719 */ switch (region_code2) {
                case 1:
                    /*  7721 */ name = "Hodh Ech Chargui";
                    /*  7722 */ break;
                case 2:
                    /*  7724 */ name = "Hodh El Gharbi";
                    /*  7725 */ break;
                case 3:
                    /*  7727 */ name = "Assaba";
                    /*  7728 */ break;
                case 4:
                    /*  7730 */ name = "Gorgol";
                    /*  7731 */ break;
                case 5:
                    /*  7733 */ name = "Brakna";
                    /*  7734 */ break;
                case 6:
                    /*  7736 */ name = "Trarza";
                    /*  7737 */ break;
                case 7:
                    /*  7739 */ name = "Adrar";
                    /*  7740 */ break;
                case 8:
                    /*  7742 */ name = "Dakhlet Nouadhibou";
                    /*  7743 */ break;
                case 9:
                    /*  7745 */ name = "Tagant";
                    /*  7746 */ break;
                case 10:
                    /*  7748 */ name = "Guidimaka";
                    /*  7749 */ break;
                case 11:
                    /*  7751 */ name = "Tiris Zemmour";
                    /*  7752 */ break;
                case 12:
                    /*  7754 */ name = "Inchiri";
            }
        }

        /*  7758 */ if (country_code.equals("MS") == true) {
            /*  7759 */ switch (region_code2) {
                case 1:
                    /*  7761 */ name = "Saint Anthony";
                    /*  7762 */ break;
                case 2:
                    /*  7764 */ name = "Saint Georges";
                    /*  7765 */ break;
                case 3:
                    /*  7767 */ name = "Saint Peter";
            }
        }

        /*  7771 */ if (country_code.equals("MU") == true) {
            /*  7772 */ switch (region_code2) {
                case 12:
                    /*  7774 */ name = "Black River";
                    /*  7775 */ break;
                case 13:
                    /*  7777 */ name = "Flacq";
                    /*  7778 */ break;
                case 14:
                    /*  7780 */ name = "Grand Port";
                    /*  7781 */ break;
                case 15:
                    /*  7783 */ name = "Moka";
                    /*  7784 */ break;
                case 16:
                    /*  7786 */ name = "Pamplemousses";
                    /*  7787 */ break;
                case 17:
                    /*  7789 */ name = "Plaines Wilhems";
                    /*  7790 */ break;
                case 18:
                    /*  7792 */ name = "Port Louis";
                    /*  7793 */ break;
                case 19:
                    /*  7795 */ name = "Riviere du Rempart";
                    /*  7796 */ break;
                case 20:
                    /*  7798 */ name = "Savanne";
                    /*  7799 */ break;
                case 21:
                    /*  7801 */ name = "Agalega Islands";
                    /*  7802 */ break;
                case 22:
                    /*  7804 */ name = "Cargados Carajos";
                    /*  7805 */ break;
                case 23:
                    /*  7807 */ name = "Rodrigues";
            }
        }

        /*  7811 */ if (country_code.equals("MV") == true) /*  7812 */ {
            switch (region_code2) {
                case 1:
                    /*  7814 */ name = "Seenu";
                    /*  7815 */ break;
                case 5:
                    /*  7817 */ name = "Laamu";
                    /*  7818 */ break;
                case 30:
                    /*  7820 */ name = "Alifu";
                    /*  7821 */ break;
                case 31:
                    /*  7823 */ name = "Baa";
                    /*  7824 */ break;
                case 32:
                    /*  7826 */ name = "Dhaalu";
                    /*  7827 */ break;
                case 33:
                    /*  7829 */ name = "Faafu ";
                    /*  7830 */ break;
                case 34:
                    /*  7832 */ name = "Gaafu Alifu";
                    /*  7833 */ break;
                case 35:
                    /*  7835 */ name = "Gaafu Dhaalu";
                    /*  7836 */ break;
                case 36:
                    /*  7838 */ name = "Haa Alifu";
                    /*  7839 */ break;
                case 37:
                    /*  7841 */ name = "Haa Dhaalu";
                    /*  7842 */ break;
                case 38:
                    /*  7844 */ name = "Kaafu";
                    /*  7845 */ break;
                case 39:
                    /*  7847 */ name = "Lhaviyani";
                    /*  7848 */ break;
                case 40:
                    /*  7850 */ name = "Maale";
                    /*  7851 */ break;
                case 41:
                    /*  7853 */ name = "Meemu";
                    /*  7854 */ break;
                case 42:
                    /*  7856 */ name = "Gnaviyani";
                    /*  7857 */ break;
                case 43:
                    /*  7859 */ name = "Noonu";
                    /*  7860 */ break;
                case 44:
                    /*  7862 */ name = "Raa";
                    /*  7863 */ break;
                case 45:
                    /*  7865 */ name = "Shaviyani";
                    /*  7866 */ break;
                case 46:
                    /*  7868 */ name = "Thaa";
                    /*  7869 */ break;
                case 47:
                    /*  7871 */ name = "Vaavu";
                case 2:
                case 3:
                case 4:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                /*  7875 */ case 29:
            }
        }
        if (country_code.equals("MW") == true) /*  7876 */ {
            switch (region_code2) {
                case 2:
                    /*  7878 */ name = "Chikwawa";
                    /*  7879 */ break;
                case 3:
                    /*  7881 */ name = "Chiradzulu";
                    /*  7882 */ break;
                case 4:
                    /*  7884 */ name = "Chitipa";
                    /*  7885 */ break;
                case 5:
                    /*  7887 */ name = "Thyolo";
                    /*  7888 */ break;
                case 6:
                    /*  7890 */ name = "Dedza";
                    /*  7891 */ break;
                case 7:
                    /*  7893 */ name = "Dowa";
                    /*  7894 */ break;
                case 8:
                    /*  7896 */ name = "Karonga";
                    /*  7897 */ break;
                case 9:
                    /*  7899 */ name = "Kasungu";
                    /*  7900 */ break;
                case 11:
                    /*  7902 */ name = "Lilongwe";
                    /*  7903 */ break;
                case 12:
                    /*  7905 */ name = "Mangochi";
                    /*  7906 */ break;
                case 13:
                    /*  7908 */ name = "Mchinji";
                    /*  7909 */ break;
                case 15:
                    /*  7911 */ name = "Mzimba";
                    /*  7912 */ break;
                case 16:
                    /*  7914 */ name = "Ntcheu";
                    /*  7915 */ break;
                case 17:
                    /*  7917 */ name = "Nkhata Bay";
                    /*  7918 */ break;
                case 18:
                    /*  7920 */ name = "Nkhotakota";
                    /*  7921 */ break;
                case 19:
                    /*  7923 */ name = "Nsanje";
                    /*  7924 */ break;
                case 20:
                    /*  7926 */ name = "Ntchisi";
                    /*  7927 */ break;
                case 21:
                    /*  7929 */ name = "Rumphi";
                    /*  7930 */ break;
                case 22:
                    /*  7932 */ name = "Salima";
                    /*  7933 */ break;
                case 23:
                    /*  7935 */ name = "Zomba";
                    /*  7936 */ break;
                case 24:
                    /*  7938 */ name = "Blantyre";
                    /*  7939 */ break;
                case 25:
                    /*  7941 */ name = "Mwanza";
                    /*  7942 */ break;
                case 26:
                    /*  7944 */ name = "Balaka";
                    /*  7945 */ break;
                case 27:
                    /*  7947 */ name = "Likoma";
                    /*  7948 */ break;
                case 28:
                    /*  7950 */ name = "Machinga";
                    /*  7951 */ break;
                case 29:
                    /*  7953 */ name = "Mulanje";
                    /*  7954 */ break;
                case 30:
                    /*  7956 */ name = "Phalombe";
                case 10:
                case 14:
            }
        }
        /*  7960 */ if (country_code.equals("MX") == true) {
            /*  7961 */ switch (region_code2) {
                case 1:
                    /*  7963 */ name = "Aguascalientes";
                    /*  7964 */ break;
                case 2:
                    /*  7966 */ name = "Baja California";
                    /*  7967 */ break;
                case 3:
                    /*  7969 */ name = "Baja California Sur";
                    /*  7970 */ break;
                case 4:
                    /*  7972 */ name = "Campeche";
                    /*  7973 */ break;
                case 5:
                    /*  7975 */ name = "Chiapas";
                    /*  7976 */ break;
                case 6:
                    /*  7978 */ name = "Chihuahua";
                    /*  7979 */ break;
                case 7:
                    /*  7981 */ name = "Coahuila de Zaragoza";
                    /*  7982 */ break;
                case 8:
                    /*  7984 */ name = "Colima";
                    /*  7985 */ break;
                case 9:
                    /*  7987 */ name = "Distrito Federal";
                    /*  7988 */ break;
                case 10:
                    /*  7990 */ name = "Durango";
                    /*  7991 */ break;
                case 11:
                    /*  7993 */ name = "Guanajuato";
                    /*  7994 */ break;
                case 12:
                    /*  7996 */ name = "Guerrero";
                    /*  7997 */ break;
                case 13:
                    /*  7999 */ name = "Hidalgo";
                    /*  8000 */ break;
                case 14:
                    /*  8002 */ name = "Jalisco";
                    /*  8003 */ break;
                case 15:
                    /*  8005 */ name = "Mexico";
                    /*  8006 */ break;
                case 16:
                    /*  8008 */ name = "Michoacan de Ocampo";
                    /*  8009 */ break;
                case 17:
                    /*  8011 */ name = "Morelos";
                    /*  8012 */ break;
                case 18:
                    /*  8014 */ name = "Nayarit";
                    /*  8015 */ break;
                case 19:
                    /*  8017 */ name = "Nuevo Leon";
                    /*  8018 */ break;
                case 20:
                    /*  8020 */ name = "Oaxaca";
                    /*  8021 */ break;
                case 21:
                    /*  8023 */ name = "Puebla";
                    /*  8024 */ break;
                case 22:
                    /*  8026 */ name = "Queretaro de Arteaga";
                    /*  8027 */ break;
                case 23:
                    /*  8029 */ name = "Quintana Roo";
                    /*  8030 */ break;
                case 24:
                    /*  8032 */ name = "San Luis Potosi";
                    /*  8033 */ break;
                case 25:
                    /*  8035 */ name = "Sinaloa";
                    /*  8036 */ break;
                case 26:
                    /*  8038 */ name = "Sonora";
                    /*  8039 */ break;
                case 27:
                    /*  8041 */ name = "Tabasco";
                    /*  8042 */ break;
                case 28:
                    /*  8044 */ name = "Tamaulipas";
                    /*  8045 */ break;
                case 29:
                    /*  8047 */ name = "Tlaxcala";
                    /*  8048 */ break;
                case 30:
                    /*  8050 */ name = "Veracruz-Llave";
                    /*  8051 */ break;
                case 31:
                    /*  8053 */ name = "Yucatan";
                    /*  8054 */ break;
                case 32:
                    /*  8056 */ name = "Zacatecas";
            }
        }

        /*  8060 */ if (country_code.equals("MY") == true) {
            /*  8061 */ switch (region_code2) {
                case 1:
                    /*  8063 */ name = "Johor";
                    /*  8064 */ break;
                case 2:
                    /*  8066 */ name = "Kedah";
                    /*  8067 */ break;
                case 3:
                    /*  8069 */ name = "Kelantan";
                    /*  8070 */ break;
                case 4:
                    /*  8072 */ name = "Melaka";
                    /*  8073 */ break;
                case 5:
                    /*  8075 */ name = "Negeri Sembilan";
                    /*  8076 */ break;
                case 6:
                    /*  8078 */ name = "Pahang";
                    /*  8079 */ break;
                case 7:
                    /*  8081 */ name = "Perak";
                    /*  8082 */ break;
                case 8:
                    /*  8084 */ name = "Perlis";
                    /*  8085 */ break;
                case 9:
                    /*  8087 */ name = "Pulau Pinang";
                    /*  8088 */ break;
                case 11:
                    /*  8090 */ name = "Sarawak";
                    /*  8091 */ break;
                case 12:
                    /*  8093 */ name = "Selangor";
                    /*  8094 */ break;
                case 13:
                    /*  8096 */ name = "Terengganu";
                    /*  8097 */ break;
                case 14:
                    /*  8099 */ name = "Kuala Lumpur";
                    /*  8100 */ break;
                case 15:
                    /*  8102 */ name = "Labuan";
                    /*  8103 */ break;
                case 16:
                    /*  8105 */ name = "Sabah";
                    /*  8106 */ break;
                case 17:
                    /*  8108 */ name = "Putrajaya";
                case 10:
            }
        }
        /*  8112 */ if (country_code.equals("MZ") == true) {
            /*  8113 */ switch (region_code2) {
                case 1:
                    /*  8115 */ name = "Cabo Delgado";
                    /*  8116 */ break;
                case 2:
                    /*  8118 */ name = "Gaza";
                    /*  8119 */ break;
                case 3:
                    /*  8121 */ name = "Inhambane";
                    /*  8122 */ break;
                case 4:
                    /*  8124 */ name = "Maputo";
                    /*  8125 */ break;
                case 5:
                    /*  8127 */ name = "Sofala";
                    /*  8128 */ break;
                case 6:
                    /*  8130 */ name = "Nampula";
                    /*  8131 */ break;
                case 7:
                    /*  8133 */ name = "Niassa";
                    /*  8134 */ break;
                case 8:
                    /*  8136 */ name = "Tete";
                    /*  8137 */ break;
                case 9:
                    /*  8139 */ name = "Zambezia";
                    /*  8140 */ break;
                case 10:
                    /*  8142 */ name = "Manica";
                    /*  8143 */ break;
                case 11:
                    /*  8145 */ name = "Maputo";
            }
        }

        /*  8149 */ if (country_code.equals("NA") == true) {
            /*  8150 */ switch (region_code2) {
                case 1:
                    /*  8152 */ name = "Bethanien";
                    /*  8153 */ break;
                case 2:
                    /*  8155 */ name = "Caprivi Oos";
                    /*  8156 */ break;
                case 3:
                    /*  8158 */ name = "Boesmanland";
                    /*  8159 */ break;
                case 4:
                    /*  8161 */ name = "Gobabis";
                    /*  8162 */ break;
                case 5:
                    /*  8164 */ name = "Grootfontein";
                    /*  8165 */ break;
                case 6:
                    /*  8167 */ name = "Kaokoland";
                    /*  8168 */ break;
                case 7:
                    /*  8170 */ name = "Karibib";
                    /*  8171 */ break;
                case 8:
                    /*  8173 */ name = "Keetmanshoop";
                    /*  8174 */ break;
                case 9:
                    /*  8176 */ name = "Luderitz";
                    /*  8177 */ break;
                case 10:
                    /*  8179 */ name = "Maltahohe";
                    /*  8180 */ break;
                case 11:
                    /*  8182 */ name = "Okahandja";
                    /*  8183 */ break;
                case 12:
                    /*  8185 */ name = "Omaruru";
                    /*  8186 */ break;
                case 13:
                    /*  8188 */ name = "Otjiwarongo";
                    /*  8189 */ break;
                case 14:
                    /*  8191 */ name = "Outjo";
                    /*  8192 */ break;
                case 15:
                    /*  8194 */ name = "Owambo";
                    /*  8195 */ break;
                case 16:
                    /*  8197 */ name = "Rehoboth";
                    /*  8198 */ break;
                case 17:
                    /*  8200 */ name = "Swakopmund";
                    /*  8201 */ break;
                case 18:
                    /*  8203 */ name = "Tsumeb";
                    /*  8204 */ break;
                case 20:
                    /*  8206 */ name = "Karasburg";
                    /*  8207 */ break;
                case 21:
                    /*  8209 */ name = "Windhoek";
                    /*  8210 */ break;
                case 22:
                    /*  8212 */ name = "Damaraland";
                    /*  8213 */ break;
                case 23:
                    /*  8215 */ name = "Hereroland Oos";
                    /*  8216 */ break;
                case 24:
                    /*  8218 */ name = "Hereroland Wes";
                    /*  8219 */ break;
                case 25:
                    /*  8221 */ name = "Kavango";
                    /*  8222 */ break;
                case 26:
                    /*  8224 */ name = "Mariental";
                    /*  8225 */ break;
                case 27:
                    /*  8227 */ name = "Namaland";
                    /*  8228 */ break;
                case 28:
                    /*  8230 */ name = "Caprivi";
                    /*  8231 */ break;
                case 29:
                    /*  8233 */ name = "Erongo";
                    /*  8234 */ break;
                case 30:
                    /*  8236 */ name = "Hardap";
                    /*  8237 */ break;
                case 31:
                    /*  8239 */ name = "Karas";
                    /*  8240 */ break;
                case 32:
                    /*  8242 */ name = "Kunene";
                    /*  8243 */ break;
                case 33:
                    /*  8245 */ name = "Ohangwena";
                    /*  8246 */ break;
                case 34:
                    /*  8248 */ name = "Okavango";
                    /*  8249 */ break;
                case 35:
                    /*  8251 */ name = "Omaheke";
                    /*  8252 */ break;
                case 36:
                    /*  8254 */ name = "Omusati";
                    /*  8255 */ break;
                case 37:
                    /*  8257 */ name = "Oshana";
                    /*  8258 */ break;
                case 38:
                    /*  8260 */ name = "Oshikoto";
                    /*  8261 */ break;
                case 39:
                    /*  8263 */ name = "Otjozondjupa";
                case 19:
            }
        }
        /*  8267 */ if (country_code.equals("NE") == true) {
            /*  8268 */ switch (region_code2) {
                case 1:
                    /*  8270 */ name = "Agadez";
                    /*  8271 */ break;
                case 2:
                    /*  8273 */ name = "Diffa";
                    /*  8274 */ break;
                case 3:
                    /*  8276 */ name = "Dosso";
                    /*  8277 */ break;
                case 4:
                    /*  8279 */ name = "Maradi";
                    /*  8280 */ break;
                case 5:
                    /*  8282 */ name = "Niamey";
                    /*  8283 */ break;
                case 6:
                    /*  8285 */ name = "Tahoua";
                    /*  8286 */ break;
                case 7:
                    /*  8288 */ name = "Zinder";
                    /*  8289 */ break;
                case 8:
                    /*  8291 */ name = "Niamey";
            }
        }

        /*  8295 */ if (country_code.equals("NG") == true) /*  8296 */ {
            switch (region_code2) {
                case 5:
                    /*  8298 */ name = "Lagos";
                    /*  8299 */ break;
                case 11:
                    /*  8301 */ name = "Federal Capital Territory";
                    /*  8302 */ break;
                case 16:
                    /*  8304 */ name = "Ogun";
                    /*  8305 */ break;
                case 21:
                    /*  8307 */ name = "Akwa Ibom";
                    /*  8308 */ break;
                case 22:
                    /*  8310 */ name = "Cross River";
                    /*  8311 */ break;
                case 23:
                    /*  8313 */ name = "Kaduna";
                    /*  8314 */ break;
                case 24:
                    /*  8316 */ name = "Katsina";
                    /*  8317 */ break;
                case 25:
                    /*  8319 */ name = "Anambra";
                    /*  8320 */ break;
                case 26:
                    /*  8322 */ name = "Benue";
                    /*  8323 */ break;
                case 27:
                    /*  8325 */ name = "Borno";
                    /*  8326 */ break;
                case 28:
                    /*  8328 */ name = "Imo";
                    /*  8329 */ break;
                case 29:
                    /*  8331 */ name = "Kano";
                    /*  8332 */ break;
                case 30:
                    /*  8334 */ name = "Kwara";
                    /*  8335 */ break;
                case 31:
                    /*  8337 */ name = "Niger";
                    /*  8338 */ break;
                case 32:
                    /*  8340 */ name = "Oyo";
                    /*  8341 */ break;
                case 35:
                    /*  8343 */ name = "Adamawa";
                    /*  8344 */ break;
                case 36:
                    /*  8346 */ name = "Delta";
                    /*  8347 */ break;
                case 37:
                    /*  8349 */ name = "Edo";
                    /*  8350 */ break;
                case 39:
                    /*  8352 */ name = "Jigawa";
                    /*  8353 */ break;
                case 40:
                    /*  8355 */ name = "Kebbi";
                    /*  8356 */ break;
                case 41:
                    /*  8358 */ name = "Kogi";
                    /*  8359 */ break;
                case 42:
                    /*  8361 */ name = "Osun";
                    /*  8362 */ break;
                case 43:
                    /*  8364 */ name = "Taraba";
                    /*  8365 */ break;
                case 44:
                    /*  8367 */ name = "Yobe";
                    /*  8368 */ break;
                case 45:
                    /*  8370 */ name = "Abia";
                    /*  8371 */ break;
                case 46:
                    /*  8373 */ name = "Bauchi";
                    /*  8374 */ break;
                case 47:
                    /*  8376 */ name = "Enugu";
                    /*  8377 */ break;
                case 48:
                    /*  8379 */ name = "Ondo";
                    /*  8380 */ break;
                case 49:
                    /*  8382 */ name = "Plateau";
                    /*  8383 */ break;
                case 50:
                    /*  8385 */ name = "Rivers";
                    /*  8386 */ break;
                case 51:
                    /*  8388 */ name = "Sokoto";
                    /*  8389 */ break;
                case 52:
                    /*  8391 */ name = "Bayelsa";
                    /*  8392 */ break;
                case 53:
                    /*  8394 */ name = "Ebonyi";
                    /*  8395 */ break;
                case 54:
                    /*  8397 */ name = "Ekiti";
                    /*  8398 */ break;
                case 55:
                    /*  8400 */ name = "Gombe";
                    /*  8401 */ break;
                case 56:
                    /*  8403 */ name = "Nassarawa";
                    /*  8404 */ break;
                case 57:
                    /*  8406 */ name = "Zamfara";
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 13:
                case 14:
                case 15:
                case 17:
                case 18:
                case 19:
                case 20:
                case 33:
                case 34:
                /*  8410 */ case 38:
            }
        }
        if (country_code.equals("NI") == true) {
            /*  8411 */ switch (region_code2) {
                case 1:
                    /*  8413 */ name = "Boaco";
                    /*  8414 */ break;
                case 2:
                    /*  8416 */ name = "Carazo";
                    /*  8417 */ break;
                case 3:
                    /*  8419 */ name = "Chinandega";
                    /*  8420 */ break;
                case 4:
                    /*  8422 */ name = "Chontales";
                    /*  8423 */ break;
                case 5:
                    /*  8425 */ name = "Esteli";
                    /*  8426 */ break;
                case 6:
                    /*  8428 */ name = "Granada";
                    /*  8429 */ break;
                case 7:
                    /*  8431 */ name = "Jinotega";
                    /*  8432 */ break;
                case 8:
                    /*  8434 */ name = "Leon";
                    /*  8435 */ break;
                case 9:
                    /*  8437 */ name = "Madriz";
                    /*  8438 */ break;
                case 10:
                    /*  8440 */ name = "Managua";
                    /*  8441 */ break;
                case 11:
                    /*  8443 */ name = "Masaya";
                    /*  8444 */ break;
                case 12:
                    /*  8446 */ name = "Matagalpa";
                    /*  8447 */ break;
                case 13:
                    /*  8449 */ name = "Nueva Segovia";
                    /*  8450 */ break;
                case 14:
                    /*  8452 */ name = "Rio San Juan";
                    /*  8453 */ break;
                case 15:
                    /*  8455 */ name = "Rivas";
                    /*  8456 */ break;
                case 16:
                    /*  8458 */ name = "Zelaya";
                    /*  8459 */ break;
                case 17:
                    /*  8461 */ name = "Autonoma Atlantico Norte";
                    /*  8462 */ break;
                case 18:
                    /*  8464 */ name = "Region Autonoma Atlantico Sur";
            }
        }

        /*  8468 */ if (country_code.equals("NL") == true) /*  8469 */ {
            switch (region_code2) {
                case 1:
                    /*  8471 */ name = "Drenthe";
                    /*  8472 */ break;
                case 2:
                    /*  8474 */ name = "Friesland";
                    /*  8475 */ break;
                case 3:
                    /*  8477 */ name = "Gelderland";
                    /*  8478 */ break;
                case 4:
                    /*  8480 */ name = "Groningen";
                    /*  8481 */ break;
                case 5:
                    /*  8483 */ name = "Limburg";
                    /*  8484 */ break;
                case 6:
                    /*  8486 */ name = "Noord-Brabant";
                    /*  8487 */ break;
                case 7:
                    /*  8489 */ name = "Noord-Holland";
                    /*  8490 */ break;
                case 9:
                    /*  8492 */ name = "Utrecht";
                    /*  8493 */ break;
                case 10:
                    /*  8495 */ name = "Zeeland";
                    /*  8496 */ break;
                case 11:
                    /*  8498 */ name = "Zuid-Holland";
                    /*  8499 */ break;
                case 15:
                    /*  8501 */ name = "Overijssel";
                    /*  8502 */ break;
                case 16:
                    /*  8504 */ name = "Flevoland";
                case 8:
                case 12:
                case 13:
                /*  8508 */ case 14:
            }
        }
        if (country_code.equals("NO") == true) {
            /*  8509 */ switch (region_code2) {
                case 1:
                    /*  8511 */ name = "Akershus";
                    /*  8512 */ break;
                case 2:
                    /*  8514 */ name = "Aust-Agder";
                    /*  8515 */ break;
                case 4:
                    /*  8517 */ name = "Buskerud";
                    /*  8518 */ break;
                case 5:
                    /*  8520 */ name = "Finnmark";
                    /*  8521 */ break;
                case 6:
                    /*  8523 */ name = "Hedmark";
                    /*  8524 */ break;
                case 7:
                    /*  8526 */ name = "Hordaland";
                    /*  8527 */ break;
                case 8:
                    /*  8529 */ name = "More og Romsdal";
                    /*  8530 */ break;
                case 9:
                    /*  8532 */ name = "Nordland";
                    /*  8533 */ break;
                case 10:
                    /*  8535 */ name = "Nord-Trondelag";
                    /*  8536 */ break;
                case 11:
                    /*  8538 */ name = "Oppland";
                    /*  8539 */ break;
                case 12:
                    /*  8541 */ name = "Oslo";
                    /*  8542 */ break;
                case 13:
                    /*  8544 */ name = "Ostfold";
                    /*  8545 */ break;
                case 14:
                    /*  8547 */ name = "Rogaland";
                    /*  8548 */ break;
                case 15:
                    /*  8550 */ name = "Sogn og Fjordane";
                    /*  8551 */ break;
                case 16:
                    /*  8553 */ name = "Sor-Trondelag";
                    /*  8554 */ break;
                case 17:
                    /*  8556 */ name = "Telemark";
                    /*  8557 */ break;
                case 18:
                    /*  8559 */ name = "Troms";
                    /*  8560 */ break;
                case 19:
                    /*  8562 */ name = "Vest-Agder";
                    /*  8563 */ break;
                case 20:
                    /*  8565 */ name = "Vestfold";
                case 3:
            }
        }
        /*  8569 */ if (country_code.equals("NP") == true) {
            /*  8570 */ switch (region_code2) {
                case 1:
                    /*  8572 */ name = "Bagmati";
                    /*  8573 */ break;
                case 2:
                    /*  8575 */ name = "Bheri";
                    /*  8576 */ break;
                case 3:
                    /*  8578 */ name = "Dhawalagiri";
                    /*  8579 */ break;
                case 4:
                    /*  8581 */ name = "Gandaki";
                    /*  8582 */ break;
                case 5:
                    /*  8584 */ name = "Janakpur";
                    /*  8585 */ break;
                case 6:
                    /*  8587 */ name = "Karnali";
                    /*  8588 */ break;
                case 7:
                    /*  8590 */ name = "Kosi";
                    /*  8591 */ break;
                case 8:
                    /*  8593 */ name = "Lumbini";
                    /*  8594 */ break;
                case 9:
                    /*  8596 */ name = "Mahakali";
                    /*  8597 */ break;
                case 10:
                    /*  8599 */ name = "Mechi";
                    /*  8600 */ break;
                case 11:
                    /*  8602 */ name = "Narayani";
                    /*  8603 */ break;
                case 12:
                    /*  8605 */ name = "Rapti";
                    /*  8606 */ break;
                case 13:
                    /*  8608 */ name = "Sagarmatha";
                    /*  8609 */ break;
                case 14:
                    /*  8611 */ name = "Seti";
            }
        }

        /*  8615 */ if (country_code.equals("NR") == true) {
            /*  8616 */ switch (region_code2) {
                case 1:
                    /*  8618 */ name = "Aiwo";
                    /*  8619 */ break;
                case 2:
                    /*  8621 */ name = "Anabar";
                    /*  8622 */ break;
                case 3:
                    /*  8624 */ name = "Anetan";
                    /*  8625 */ break;
                case 4:
                    /*  8627 */ name = "Anibare";
                    /*  8628 */ break;
                case 5:
                    /*  8630 */ name = "Baiti";
                    /*  8631 */ break;
                case 6:
                    /*  8633 */ name = "Boe";
                    /*  8634 */ break;
                case 7:
                    /*  8636 */ name = "Buada";
                    /*  8637 */ break;
                case 8:
                    /*  8639 */ name = "Denigomodu";
                    /*  8640 */ break;
                case 9:
                    /*  8642 */ name = "Ewa";
                    /*  8643 */ break;
                case 10:
                    /*  8645 */ name = "Ijuw";
                    /*  8646 */ break;
                case 11:
                    /*  8648 */ name = "Meneng";
                    /*  8649 */ break;
                case 12:
                    /*  8651 */ name = "Nibok";
                    /*  8652 */ break;
                case 13:
                    /*  8654 */ name = "Uaboe";
                    /*  8655 */ break;
                case 14:
                    /*  8657 */ name = "Yaren";
            }
        }

        /*  8661 */ if (country_code.equals("NZ") == true) {
            /*  8662 */ switch (region_code2) {
                case 10:
                    /*  8664 */ name = "Chatham Islands";
                    /*  8665 */ break;
                case 1010:
                    /*  8667 */ name = "Auckland";
                    /*  8668 */ break;
                case 1011:
                    /*  8670 */ name = "Bay of Plenty";
                    /*  8671 */ break;
                case 1012:
                    /*  8673 */ name = "Canterbury";
                    /*  8674 */ break;
                case 1047:
                    /*  8676 */ name = "Gisborne";
                    /*  8677 */ break;
                case 1048:
                    /*  8679 */ name = "Hawke's Bay";
                    /*  8680 */ break;
                case 1049:
                    /*  8682 */ name = "Manawatu-Wanganui";
                    /*  8683 */ break;
                case 1050:
                    /*  8685 */ name = "Marlborough";
                    /*  8686 */ break;
                case 1051:
                    /*  8688 */ name = "Nelson";
                    /*  8689 */ break;
                case 1052:
                    /*  8691 */ name = "Northland";
                    /*  8692 */ break;
                case 1053:
                    /*  8694 */ name = "Otago";
                    /*  8695 */ break;
                case 1054:
                    /*  8697 */ name = "Southland";
                    /*  8698 */ break;
                case 1055:
                    /*  8700 */ name = "Taranaki";
                    /*  8701 */ break;
                case 1090:
                    /*  8703 */ name = "Waikato";
                    /*  8704 */ break;
                case 1091:
                    /*  8706 */ name = "Wellington";
                    /*  8707 */ break;
                case 1092:
                    /*  8709 */ name = "West Coast";
            }
        }

        /*  8713 */ if (country_code.equals("OM") == true) {
            /*  8714 */ switch (region_code2) {
                case 1:
                    /*  8716 */ name = "Ad Dakhiliyah";
                    /*  8717 */ break;
                case 2:
                    /*  8719 */ name = "Al Batinah";
                    /*  8720 */ break;
                case 3:
                    /*  8722 */ name = "Al Wusta";
                    /*  8723 */ break;
                case 4:
                    /*  8725 */ name = "Ash Sharqiyah";
                    /*  8726 */ break;
                case 5:
                    /*  8728 */ name = "Az Zahirah";
                    /*  8729 */ break;
                case 6:
                    /*  8731 */ name = "Masqat";
                    /*  8732 */ break;
                case 7:
                    /*  8734 */ name = "Musandam";
                    /*  8735 */ break;
                case 8:
                    /*  8737 */ name = "Zufar";
            }
        }

        /*  8741 */ if (country_code.equals("PA") == true) {
            /*  8742 */ switch (region_code2) {
                case 1:
                    /*  8744 */ name = "Bocas del Toro";
                    /*  8745 */ break;
                case 2:
                    /*  8747 */ name = "Chiriqui";
                    /*  8748 */ break;
                case 3:
                    /*  8750 */ name = "Cocle";
                    /*  8751 */ break;
                case 4:
                    /*  8753 */ name = "Colon";
                    /*  8754 */ break;
                case 5:
                    /*  8756 */ name = "Darien";
                    /*  8757 */ break;
                case 6:
                    /*  8759 */ name = "Herrera";
                    /*  8760 */ break;
                case 7:
                    /*  8762 */ name = "Los Santos";
                    /*  8763 */ break;
                case 8:
                    /*  8765 */ name = "Panama";
                    /*  8766 */ break;
                case 9:
                    /*  8768 */ name = "San Blas";
                    /*  8769 */ break;
                case 10:
                    /*  8771 */ name = "Veraguas";
            }
        }

        /*  8775 */ if (country_code.equals("PE") == true) {
            /*  8776 */ switch (region_code2) {
                case 1:
                    /*  8778 */ name = "Amazonas";
                    /*  8779 */ break;
                case 2:
                    /*  8781 */ name = "Ancash";
                    /*  8782 */ break;
                case 3:
                    /*  8784 */ name = "Apurimac";
                    /*  8785 */ break;
                case 4:
                    /*  8787 */ name = "Arequipa";
                    /*  8788 */ break;
                case 5:
                    /*  8790 */ name = "Ayacucho";
                    /*  8791 */ break;
                case 6:
                    /*  8793 */ name = "Cajamarca";
                    /*  8794 */ break;
                case 7:
                    /*  8796 */ name = "Callao";
                    /*  8797 */ break;
                case 8:
                    /*  8799 */ name = "Cusco";
                    /*  8800 */ break;
                case 9:
                    /*  8802 */ name = "Huancavelica";
                    /*  8803 */ break;
                case 10:
                    /*  8805 */ name = "Huanuco";
                    /*  8806 */ break;
                case 11:
                    /*  8808 */ name = "Ica";
                    /*  8809 */ break;
                case 12:
                    /*  8811 */ name = "Junin";
                    /*  8812 */ break;
                case 13:
                    /*  8814 */ name = "La Libertad";
                    /*  8815 */ break;
                case 14:
                    /*  8817 */ name = "Lambayeque";
                    /*  8818 */ break;
                case 15:
                    /*  8820 */ name = "Lima";
                    /*  8821 */ break;
                case 16:
                    /*  8823 */ name = "Loreto";
                    /*  8824 */ break;
                case 17:
                    /*  8826 */ name = "Madre de Dios";
                    /*  8827 */ break;
                case 18:
                    /*  8829 */ name = "Moquegua";
                    /*  8830 */ break;
                case 19:
                    /*  8832 */ name = "Pasco";
                    /*  8833 */ break;
                case 20:
                    /*  8835 */ name = "Piura";
                    /*  8836 */ break;
                case 21:
                    /*  8838 */ name = "Puno";
                    /*  8839 */ break;
                case 22:
                    /*  8841 */ name = "San Martin";
                    /*  8842 */ break;
                case 23:
                    /*  8844 */ name = "Tacna";
                    /*  8845 */ break;
                case 24:
                    /*  8847 */ name = "Tumbes";
                    /*  8848 */ break;
                case 25:
                    /*  8850 */ name = "Ucayali";
            }
        }

        /*  8854 */ if (country_code.equals("PG") == true) {
            /*  8855 */ switch (region_code2) {
                case 1:
                    /*  8857 */ name = "Central";
                    /*  8858 */ break;
                case 2:
                    /*  8860 */ name = "Gulf";
                    /*  8861 */ break;
                case 3:
                    /*  8863 */ name = "Milne Bay";
                    /*  8864 */ break;
                case 4:
                    /*  8866 */ name = "Northern";
                    /*  8867 */ break;
                case 5:
                    /*  8869 */ name = "Southern Highlands";
                    /*  8870 */ break;
                case 6:
                    /*  8872 */ name = "Western";
                    /*  8873 */ break;
                case 7:
                    /*  8875 */ name = "North Solomons";
                    /*  8876 */ break;
                case 8:
                    /*  8878 */ name = "Chimbu";
                    /*  8879 */ break;
                case 9:
                    /*  8881 */ name = "Eastern Highlands";
                    /*  8882 */ break;
                case 10:
                    /*  8884 */ name = "East New Britain";
                    /*  8885 */ break;
                case 11:
                    /*  8887 */ name = "East Sepik";
                    /*  8888 */ break;
                case 12:
                    /*  8890 */ name = "Madang";
                    /*  8891 */ break;
                case 13:
                    /*  8893 */ name = "Manus";
                    /*  8894 */ break;
                case 14:
                    /*  8896 */ name = "Morobe";
                    /*  8897 */ break;
                case 15:
                    /*  8899 */ name = "New Ireland";
                    /*  8900 */ break;
                case 16:
                    /*  8902 */ name = "Western Highlands";
                    /*  8903 */ break;
                case 17:
                    /*  8905 */ name = "West New Britain";
                    /*  8906 */ break;
                case 18:
                    /*  8908 */ name = "Sandaun";
                    /*  8909 */ break;
                case 19:
                    /*  8911 */ name = "Enga";
                    /*  8912 */ break;
                case 20:
                    /*  8914 */ name = "National Capital";
            }
        }

        /*  8918 */ if (country_code.equals("PH") == true) {
            /*  8919 */ switch (region_code2) {
                case 1:
                    /*  8921 */ name = "Abra";
                    /*  8922 */ break;
                case 2:
                    /*  8924 */ name = "Agusan del Norte";
                    /*  8925 */ break;
                case 3:
                    /*  8927 */ name = "Agusan del Sur";
                    /*  8928 */ break;
                case 4:
                    /*  8930 */ name = "Aklan";
                    /*  8931 */ break;
                case 5:
                    /*  8933 */ name = "Albay";
                    /*  8934 */ break;
                case 6:
                    /*  8936 */ name = "Antique";
                    /*  8937 */ break;
                case 7:
                    /*  8939 */ name = "Bataan";
                    /*  8940 */ break;
                case 8:
                    /*  8942 */ name = "Batanes";
                    /*  8943 */ break;
                case 9:
                    /*  8945 */ name = "Batangas";
                    /*  8946 */ break;
                case 10:
                    /*  8948 */ name = "Benguet";
                    /*  8949 */ break;
                case 11:
                    /*  8951 */ name = "Bohol";
                    /*  8952 */ break;
                case 12:
                    /*  8954 */ name = "Bukidnon";
                    /*  8955 */ break;
                case 13:
                    /*  8957 */ name = "Bulacan";
                    /*  8958 */ break;
                case 14:
                    /*  8960 */ name = "Cagayan";
                    /*  8961 */ break;
                case 15:
                    /*  8963 */ name = "Camarines Norte";
                    /*  8964 */ break;
                case 16:
                    /*  8966 */ name = "Camarines Sur";
                    /*  8967 */ break;
                case 17:
                    /*  8969 */ name = "Camiguin";
                    /*  8970 */ break;
                case 18:
                    /*  8972 */ name = "Capiz";
                    /*  8973 */ break;
                case 19:
                    /*  8975 */ name = "Catanduanes";
                    /*  8976 */ break;
                case 20:
                    /*  8978 */ name = "Cavite";
                    /*  8979 */ break;
                case 21:
                    /*  8981 */ name = "Cebu";
                    /*  8982 */ break;
                case 22:
                    /*  8984 */ name = "Basilan";
                    /*  8985 */ break;
                case 23:
                    /*  8987 */ name = "Eastern Samar";
                    /*  8988 */ break;
                case 24:
                    /*  8990 */ name = "Davao";
                    /*  8991 */ break;
                case 25:
                    /*  8993 */ name = "Davao del Sur";
                    /*  8994 */ break;
                case 26:
                    /*  8996 */ name = "Davao Oriental";
                    /*  8997 */ break;
                case 27:
                    /*  8999 */ name = "Ifugao";
                    /*  9000 */ break;
                case 28:
                    /*  9002 */ name = "Ilocos Norte";
                    /*  9003 */ break;
                case 29:
                    /*  9005 */ name = "Ilocos Sur";
                    /*  9006 */ break;
                case 30:
                    /*  9008 */ name = "Iloilo";
                    /*  9009 */ break;
                case 31:
                    /*  9011 */ name = "Isabela";
                    /*  9012 */ break;
                case 32:
                    /*  9014 */ name = "Kalinga-Apayao";
                    /*  9015 */ break;
                case 33:
                    /*  9017 */ name = "Laguna";
                    /*  9018 */ break;
                case 34:
                    /*  9020 */ name = "Lanao del Norte";
                    /*  9021 */ break;
                case 35:
                    /*  9023 */ name = "Lanao del Sur";
                    /*  9024 */ break;
                case 36:
                    /*  9026 */ name = "La Union";
                    /*  9027 */ break;
                case 37:
                    /*  9029 */ name = "Leyte";
                    /*  9030 */ break;
                case 38:
                    /*  9032 */ name = "Marinduque";
                    /*  9033 */ break;
                case 39:
                    /*  9035 */ name = "Masbate";
                    /*  9036 */ break;
                case 40:
                    /*  9038 */ name = "Mindoro Occidental";
                    /*  9039 */ break;
                case 41:
                    /*  9041 */ name = "Mindoro Oriental";
                    /*  9042 */ break;
                case 42:
                    /*  9044 */ name = "Misamis Occidental";
                    /*  9045 */ break;
                case 43:
                    /*  9047 */ name = "Misamis Oriental";
                    /*  9048 */ break;
                case 44:
                    /*  9050 */ name = "Mountain";
                    /*  9051 */ break;
                case 45:
                    /*  9053 */ name = "Negros Occidental";
                    /*  9054 */ break;
                case 46:
                    /*  9056 */ name = "Negros Oriental";
                    /*  9057 */ break;
                case 47:
                    /*  9059 */ name = "Nueva Ecija";
                    /*  9060 */ break;
                case 48:
                    /*  9062 */ name = "Nueva Vizcaya";
                    /*  9063 */ break;
                case 49:
                    /*  9065 */ name = "Palawan";
                    /*  9066 */ break;
                case 50:
                    /*  9068 */ name = "Pampanga";
                    /*  9069 */ break;
                case 51:
                    /*  9071 */ name = "Pangasinan";
                    /*  9072 */ break;
                case 53:
                    /*  9074 */ name = "Rizal";
                    /*  9075 */ break;
                case 54:
                    /*  9077 */ name = "Romblon";
                    /*  9078 */ break;
                case 55:
                    /*  9080 */ name = "Samar";
                    /*  9081 */ break;
                case 56:
                    /*  9083 */ name = "Maguindanao";
                    /*  9084 */ break;
                case 57:
                    /*  9086 */ name = "North Cotabato";
                    /*  9087 */ break;
                case 58:
                    /*  9089 */ name = "Sorsogon";
                    /*  9090 */ break;
                case 59:
                    /*  9092 */ name = "Southern Leyte";
                    /*  9093 */ break;
                case 60:
                    /*  9095 */ name = "Sulu";
                    /*  9096 */ break;
                case 61:
                    /*  9098 */ name = "Surigao del Norte";
                    /*  9099 */ break;
                case 62:
                    /*  9101 */ name = "Surigao del Sur";
                    /*  9102 */ break;
                case 63:
                    /*  9104 */ name = "Tarlac";
                    /*  9105 */ break;
                case 64:
                    /*  9107 */ name = "Zambales";
                    /*  9108 */ break;
                case 65:
                    /*  9110 */ name = "Zamboanga del Norte";
                    /*  9111 */ break;
                case 66:
                    /*  9113 */ name = "Zamboanga del Sur";
                    /*  9114 */ break;
                case 67:
                    /*  9116 */ name = "Northern Samar";
                    /*  9117 */ break;
                case 68:
                    /*  9119 */ name = "Quirino";
                    /*  9120 */ break;
                case 69:
                    /*  9122 */ name = "Siquijor";
                    /*  9123 */ break;
                case 70:
                    /*  9125 */ name = "South Cotabato";
                    /*  9126 */ break;
                case 71:
                    /*  9128 */ name = "Sultan Kudarat";
                    /*  9129 */ break;
                case 72:
                    /*  9131 */ name = "Tawitawi";
                    /*  9132 */ break;
                case 832:
                    /*  9134 */ name = "Angeles";
                    /*  9135 */ break;
                case 833:
                    /*  9137 */ name = "Bacolod";
                    /*  9138 */ break;
                case 834:
                    /*  9140 */ name = "Bago";
                    /*  9141 */ break;
                case 835:
                    /*  9143 */ name = "Baguio";
                    /*  9144 */ break;
                case 836:
                    /*  9146 */ name = "Bais";
                    /*  9147 */ break;
                case 837:
                    /*  9149 */ name = "Basilan City";
                    /*  9150 */ break;
                case 838:
                    /*  9152 */ name = "Batangas City";
                    /*  9153 */ break;
                case 839:
                    /*  9155 */ name = "Butuan";
                    /*  9156 */ break;
                case 840:
                    /*  9158 */ name = "Cabanatuan";
                    /*  9159 */ break;
                case 875:
                    /*  9161 */ name = "Cadiz";
                    /*  9162 */ break;
                case 876:
                    /*  9164 */ name = "Cagayan de Oro";
                    /*  9165 */ break;
                case 877:
                    /*  9167 */ name = "Calbayog";
                    /*  9168 */ break;
                case 878:
                    /*  9170 */ name = "Caloocan";
                    /*  9171 */ break;
                case 879:
                    /*  9173 */ name = "Canlaon";
                    /*  9174 */ break;
                case 880:
                    /*  9176 */ name = "Cavite City";
                    /*  9177 */ break;
                case 881:
                    /*  9179 */ name = "Cebu City";
                    /*  9180 */ break;
                case 882:
                    /*  9182 */ name = "Cotabato";
                    /*  9183 */ break;
                case 883:
                    /*  9185 */ name = "Dagupan";
                    /*  9186 */ break;
                case 918:
                    /*  9188 */ name = "Danao";
                    /*  9189 */ break;
                case 919:
                    /*  9191 */ name = "Dapitan";
                    /*  9192 */ break;
                case 920:
                    /*  9194 */ name = "Davao City";
                    /*  9195 */ break;
                case 921:
                    /*  9197 */ name = "Dipolog";
                    /*  9198 */ break;
                case 922:
                    /*  9200 */ name = "Dumaguete";
                    /*  9201 */ break;
                case 923:
                    /*  9203 */ name = "General Santos";
                    /*  9204 */ break;
                case 924:
                    /*  9206 */ name = "Gingoog";
                    /*  9207 */ break;
                case 925:
                    /*  9209 */ name = "Iligan";
                    /*  9210 */ break;
                case 926:
                    /*  9212 */ name = "Iloilo City";
                    /*  9213 */ break;
                case 961:
                    /*  9215 */ name = "Iriga";
                    /*  9216 */ break;
                case 962:
                    /*  9218 */ name = "La Carlota";
                    /*  9219 */ break;
                case 963:
                    /*  9221 */ name = "Laoag";
                    /*  9222 */ break;
                case 964:
                    /*  9224 */ name = "Lapu-Lapu";
                    /*  9225 */ break;
                case 965:
                    /*  9227 */ name = "Legaspi";
                    /*  9228 */ break;
                case 966:
                    /*  9230 */ name = "Lipa";
                    /*  9231 */ break;
                case 967:
                    /*  9233 */ name = "Lucena";
                    /*  9234 */ break;
                case 968:
                    /*  9236 */ name = "Mandaue";
                    /*  9237 */ break;
                case 969:
                    /*  9239 */ name = "Manila";
                    /*  9240 */ break;
                case 1004:
                    /*  9242 */ name = "Marawi";
                    /*  9243 */ break;
                case 1005:
                    /*  9245 */ name = "Naga";
                    /*  9246 */ break;
                case 1006:
                    /*  9248 */ name = "Olongapo";
                    /*  9249 */ break;
                case 1007:
                    /*  9251 */ name = "Ormoc";
                    /*  9252 */ break;
                case 1008:
                    /*  9254 */ name = "Oroquieta";
                    /*  9255 */ break;
                case 1009:
                    /*  9257 */ name = "Ozamis";
                    /*  9258 */ break;
                case 1010:
                    /*  9260 */ name = "Pagadian";
                    /*  9261 */ break;
                case 1011:
                    /*  9263 */ name = "Palayan";
                    /*  9264 */ break;
                case 1012:
                    /*  9266 */ name = "Pasay";
                    /*  9267 */ break;
                case 1047:
                    /*  9269 */ name = "Puerto Princesa";
                    /*  9270 */ break;
                case 1048:
                    /*  9272 */ name = "Quezon City";
                    /*  9273 */ break;
                case 1049:
                    /*  9275 */ name = "Roxas";
                    /*  9276 */ break;
                case 1050:
                    /*  9278 */ name = "San Carlos";
                    /*  9279 */ break;
                case 1051:
                    /*  9281 */ name = "San Carlos";
                    /*  9282 */ break;
                case 1052:
                    /*  9284 */ name = "San Jose";
                    /*  9285 */ break;
                case 1053:
                    /*  9287 */ name = "San Pablo";
                    /*  9288 */ break;
                case 1054:
                    /*  9290 */ name = "Silay";
                    /*  9291 */ break;
                case 1055:
                    /*  9293 */ name = "Surigao";
                    /*  9294 */ break;
                case 1090:
                    /*  9296 */ name = "Tacloban";
                    /*  9297 */ break;
                case 1091:
                    /*  9299 */ name = "Tagaytay";
                    /*  9300 */ break;
                case 1092:
                    /*  9302 */ name = "Tagbilaran";
                    /*  9303 */ break;
                case 1093:
                    /*  9305 */ name = "Tangub";
                    /*  9306 */ break;
                case 1094:
                    /*  9308 */ name = "Toledo";
                    /*  9309 */ break;
                case 1095:
                    /*  9311 */ name = "Trece Martires";
                    /*  9312 */ break;
                case 1096:
                    /*  9314 */ name = "Zamboanga";
                    /*  9315 */ break;
                case 1097:
                    /*  9317 */ name = "Aurora";
                    /*  9318 */ break;
                case 1134:
                    /*  9320 */ name = "Quezon";
                    /*  9321 */ break;
                case 1135:
                    /*  9323 */ name = "Negros Occidental";
            }
        }

        /*  9327 */ if (country_code.equals("PK") == true) {
            /*  9328 */ switch (region_code2) {
                case 1:
                    /*  9330 */ name = "Federally Administered Tribal Areas";
                    /*  9331 */ break;
                case 2:
                    /*  9333 */ name = "Balochistan";
                    /*  9334 */ break;
                case 3:
                    /*  9336 */ name = "North-West Frontier";
                    /*  9337 */ break;
                case 4:
                    /*  9339 */ name = "Punjab";
                    /*  9340 */ break;
                case 5:
                    /*  9342 */ name = "Sindh";
                    /*  9343 */ break;
                case 6:
                    /*  9345 */ name = "Azad Kashmir";
                    /*  9346 */ break;
                case 7:
                    /*  9348 */ name = "Northern Areas";
                    /*  9349 */ break;
                case 8:
                    /*  9351 */ name = "Islamabad";
            }
        }

        /*  9355 */ if (country_code.equals("PL") == true) {
            /*  9356 */ switch (region_code2) {
                case 72:
                    /*  9358 */ name = "Dolnoslaskie";
                    /*  9359 */ break;
                case 73:
                    /*  9361 */ name = "Kujawsko-Pomorskie";
                    /*  9362 */ break;
                case 74:
                    /*  9364 */ name = "Lodzkie";
                    /*  9365 */ break;
                case 75:
                    /*  9367 */ name = "Lubelskie";
                    /*  9368 */ break;
                case 76:
                    /*  9370 */ name = "Lubuskie";
                    /*  9371 */ break;
                case 77:
                    /*  9373 */ name = "Malopolskie";
                    /*  9374 */ break;
                case 78:
                    /*  9376 */ name = "Mazowieckie";
                    /*  9377 */ break;
                case 79:
                    /*  9379 */ name = "Opolskie";
                    /*  9380 */ break;
                case 80:
                    /*  9382 */ name = "Podkarpackie";
                    /*  9383 */ break;
                case 81:
                    /*  9385 */ name = "Podlaskie";
                    /*  9386 */ break;
                case 82:
                    /*  9388 */ name = "Pomorskie";
                    /*  9389 */ break;
                case 83:
                    /*  9391 */ name = "Slaskie";
                    /*  9392 */ break;
                case 84:
                    /*  9394 */ name = "Swietokrzyskie";
                    /*  9395 */ break;
                case 85:
                    /*  9397 */ name = "Warminsko-Mazurskie";
                    /*  9398 */ break;
                case 86:
                    /*  9400 */ name = "Wielkopolskie";
                    /*  9401 */ break;
                case 87:
                    /*  9403 */ name = "Zachodniopomorskie";
            }
        }

        /*  9407 */ if (country_code.equals("PS") == true) {
            /*  9408 */ switch (region_code2) {
                case 1131:
                    /*  9410 */ name = "Gaza";
                    /*  9411 */ break;
                case 1798:
                    /*  9413 */ name = "West Bank";
            }
        }

        /*  9417 */ if (country_code.equals("PT") == true) /*  9418 */ {
            switch (region_code2) {
                case 2:
                    /*  9420 */ name = "Aveiro";
                    /*  9421 */ break;
                case 3:
                    /*  9423 */ name = "Beja";
                    /*  9424 */ break;
                case 4:
                    /*  9426 */ name = "Braga";
                    /*  9427 */ break;
                case 5:
                    /*  9429 */ name = "Braganca";
                    /*  9430 */ break;
                case 6:
                    /*  9432 */ name = "Castelo Branco";
                    /*  9433 */ break;
                case 7:
                    /*  9435 */ name = "Coimbra";
                    /*  9436 */ break;
                case 8:
                    /*  9438 */ name = "Evora";
                    /*  9439 */ break;
                case 9:
                    /*  9441 */ name = "Faro";
                    /*  9442 */ break;
                case 10:
                    /*  9444 */ name = "Madeira";
                    /*  9445 */ break;
                case 11:
                    /*  9447 */ name = "Guarda";
                    /*  9448 */ break;
                case 13:
                    /*  9450 */ name = "Leiria";
                    /*  9451 */ break;
                case 14:
                    /*  9453 */ name = "Lisboa";
                    /*  9454 */ break;
                case 16:
                    /*  9456 */ name = "Portalegre";
                    /*  9457 */ break;
                case 17:
                    /*  9459 */ name = "Porto";
                    /*  9460 */ break;
                case 18:
                    /*  9462 */ name = "Santarem";
                    /*  9463 */ break;
                case 19:
                    /*  9465 */ name = "Setubal";
                    /*  9466 */ break;
                case 20:
                    /*  9468 */ name = "Viana do Castelo";
                    /*  9469 */ break;
                case 21:
                    /*  9471 */ name = "Vila Real";
                    /*  9472 */ break;
                case 22:
                    /*  9474 */ name = "Viseu";
                    /*  9475 */ break;
                case 23:
                    /*  9477 */ name = "Azores";
                case 12:
                case 15:
            }
        }
        /*  9481 */ if (country_code.equals("PY") == true) /*  9482 */ {
            switch (region_code2) {
                case 1:
                    /*  9484 */ name = "Alto Parana";
                    /*  9485 */ break;
                case 2:
                    /*  9487 */ name = "Amambay";
                    /*  9488 */ break;
                case 3:
                    /*  9490 */ name = "Boqueron";
                    /*  9491 */ break;
                case 4:
                    /*  9493 */ name = "Caaguazu";
                    /*  9494 */ break;
                case 5:
                    /*  9496 */ name = "Caazapa";
                    /*  9497 */ break;
                case 6:
                    /*  9499 */ name = "Central";
                    /*  9500 */ break;
                case 7:
                    /*  9502 */ name = "Concepcion";
                    /*  9503 */ break;
                case 8:
                    /*  9505 */ name = "Cordillera";
                    /*  9506 */ break;
                case 10:
                    /*  9508 */ name = "Guaira";
                    /*  9509 */ break;
                case 11:
                    /*  9511 */ name = "Itapua";
                    /*  9512 */ break;
                case 12:
                    /*  9514 */ name = "Misiones";
                    /*  9515 */ break;
                case 13:
                    /*  9517 */ name = "Neembucu";
                    /*  9518 */ break;
                case 15:
                    /*  9520 */ name = "Paraguari";
                    /*  9521 */ break;
                case 16:
                    /*  9523 */ name = "Presidente Hayes";
                    /*  9524 */ break;
                case 17:
                    /*  9526 */ name = "San Pedro";
                    /*  9527 */ break;
                case 19:
                    /*  9529 */ name = "Canindeyu";
                    /*  9530 */ break;
                case 20:
                    /*  9532 */ name = "Chaco";
                    /*  9533 */ break;
                case 21:
                    /*  9535 */ name = "Nueva Asuncion";
                    /*  9536 */ break;
                case 23:
                    /*  9538 */ name = "Alto Paraguay";
                case 9:
                case 14:
                case 18:
                /*  9542 */ case 22:
            }
        }
        if (country_code.equals("QA") == true) {
            /*  9543 */ switch (region_code2) {
                case 1:
                    /*  9545 */ name = "Ad Dawhah";
                    /*  9546 */ break;
                case 2:
                    /*  9548 */ name = "Al Ghuwariyah";
                    /*  9549 */ break;
                case 3:
                    /*  9551 */ name = "Al Jumaliyah";
                    /*  9552 */ break;
                case 4:
                    /*  9554 */ name = "Al Khawr";
                    /*  9555 */ break;
                case 5:
                    /*  9557 */ name = "Al Wakrah Municipality";
                    /*  9558 */ break;
                case 6:
                    /*  9560 */ name = "Ar Rayyan";
                    /*  9561 */ break;
                case 8:
                    /*  9563 */ name = "Madinat ach Shamal";
                    /*  9564 */ break;
                case 9:
                    /*  9566 */ name = "Umm Salal";
                    /*  9567 */ break;
                case 10:
                    /*  9569 */ name = "Al Wakrah";
                    /*  9570 */ break;
                case 11:
                    /*  9572 */ name = "Jariyan al Batnah";
                    /*  9573 */ break;
                case 12:
                    /*  9575 */ name = "Umm Sa'id";
                case 7:
            }
        }
        /*  9579 */ if (country_code.equals("RO") == true) {
            /*  9580 */ switch (region_code2) {
                case 1:
                    /*  9582 */ name = "Alba";
                    /*  9583 */ break;
                case 2:
                    /*  9585 */ name = "Arad";
                    /*  9586 */ break;
                case 3:
                    /*  9588 */ name = "Arges";
                    /*  9589 */ break;
                case 4:
                    /*  9591 */ name = "Bacau";
                    /*  9592 */ break;
                case 5:
                    /*  9594 */ name = "Bihor";
                    /*  9595 */ break;
                case 6:
                    /*  9597 */ name = "Bistrita-Nasaud";
                    /*  9598 */ break;
                case 7:
                    /*  9600 */ name = "Botosani";
                    /*  9601 */ break;
                case 8:
                    /*  9603 */ name = "Braila";
                    /*  9604 */ break;
                case 9:
                    /*  9606 */ name = "Brasov";
                    /*  9607 */ break;
                case 10:
                    /*  9609 */ name = "Bucuresti";
                    /*  9610 */ break;
                case 11:
                    /*  9612 */ name = "Buzau";
                    /*  9613 */ break;
                case 12:
                    /*  9615 */ name = "Caras-Severin";
                    /*  9616 */ break;
                case 13:
                    /*  9618 */ name = "Cluj";
                    /*  9619 */ break;
                case 14:
                    /*  9621 */ name = "Constanta";
                    /*  9622 */ break;
                case 15:
                    /*  9624 */ name = "Covasna";
                    /*  9625 */ break;
                case 16:
                    /*  9627 */ name = "Dambovita";
                    /*  9628 */ break;
                case 17:
                    /*  9630 */ name = "Dolj";
                    /*  9631 */ break;
                case 18:
                    /*  9633 */ name = "Galati";
                    /*  9634 */ break;
                case 19:
                    /*  9636 */ name = "Gorj";
                    /*  9637 */ break;
                case 20:
                    /*  9639 */ name = "Harghita";
                    /*  9640 */ break;
                case 21:
                    /*  9642 */ name = "Hunedoara";
                    /*  9643 */ break;
                case 22:
                    /*  9645 */ name = "Ialomita";
                    /*  9646 */ break;
                case 23:
                    /*  9648 */ name = "Iasi";
                    /*  9649 */ break;
                case 25:
                    /*  9651 */ name = "Maramures";
                    /*  9652 */ break;
                case 26:
                    /*  9654 */ name = "Mehedinti";
                    /*  9655 */ break;
                case 27:
                    /*  9657 */ name = "Mures";
                    /*  9658 */ break;
                case 28:
                    /*  9660 */ name = "Neamt";
                    /*  9661 */ break;
                case 29:
                    /*  9663 */ name = "Olt";
                    /*  9664 */ break;
                case 30:
                    /*  9666 */ name = "Prahova";
                    /*  9667 */ break;
                case 31:
                    /*  9669 */ name = "Salaj";
                    /*  9670 */ break;
                case 32:
                    /*  9672 */ name = "Satu Mare";
                    /*  9673 */ break;
                case 33:
                    /*  9675 */ name = "Sibiu";
                    /*  9676 */ break;
                case 34:
                    /*  9678 */ name = "Suceava";
                    /*  9679 */ break;
                case 35:
                    /*  9681 */ name = "Teleorman";
                    /*  9682 */ break;
                case 36:
                    /*  9684 */ name = "Timis";
                    /*  9685 */ break;
                case 37:
                    /*  9687 */ name = "Tulcea";
                    /*  9688 */ break;
                case 38:
                    /*  9690 */ name = "Vaslui";
                    /*  9691 */ break;
                case 39:
                    /*  9693 */ name = "Valcea";
                    /*  9694 */ break;
                case 40:
                    /*  9696 */ name = "Vrancea";
                    /*  9697 */ break;
                case 41:
                    /*  9699 */ name = "Calarasi";
                    /*  9700 */ break;
                case 42:
                    /*  9702 */ name = "Giurgiu";
                    /*  9703 */ break;
                case 43:
                    /*  9705 */ name = "Ilfov";
                case 24:
            }
        }
        /*  9709 */ if (country_code.equals("RS") == true) {
            /*  9710 */ switch (region_code2) {
                case 1:
                    /*  9712 */ name = "Kosovo";
                    /*  9713 */ break;
                case 2:
                    /*  9715 */ name = "Vojvodina";
            }
        }

        /*  9719 */ if (country_code.equals("RU") == true) {
            /*  9720 */ switch (region_code2) {
                case 1:
                    /*  9722 */ name = "Adygeya";
                    /*  9723 */ break;
                case 2:
                    /*  9725 */ name = "Aginsky Buryatsky AO";
                    /*  9726 */ break;
                case 3:
                    /*  9728 */ name = "Gorno-Altay";
                    /*  9729 */ break;
                case 4:
                    /*  9731 */ name = "Altaisky krai";
                    /*  9732 */ break;
                case 5:
                    /*  9734 */ name = "Amur";
                    /*  9735 */ break;
                case 6:
                    /*  9737 */ name = "Arkhangel'sk";
                    /*  9738 */ break;
                case 7:
                    /*  9740 */ name = "Astrakhan'";
                    /*  9741 */ break;
                case 8:
                    /*  9743 */ name = "Bashkortostan";
                    /*  9744 */ break;
                case 9:
                    /*  9746 */ name = "Belgorod";
                    /*  9747 */ break;
                case 10:
                    /*  9749 */ name = "Bryansk";
                    /*  9750 */ break;
                case 11:
                    /*  9752 */ name = "Buryat";
                    /*  9753 */ break;
                case 12:
                    /*  9755 */ name = "Chechnya";
                    /*  9756 */ break;
                case 13:
                    /*  9758 */ name = "Chelyabinsk";
                    /*  9759 */ break;
                case 14:
                    /*  9761 */ name = "Chita";
                    /*  9762 */ break;
                case 15:
                    /*  9764 */ name = "Chukot";
                    /*  9765 */ break;
                case 16:
                    /*  9767 */ name = "Chuvashia";
                    /*  9768 */ break;
                case 17:
                    /*  9770 */ name = "Dagestan";
                    /*  9771 */ break;
                case 18:
                    /*  9773 */ name = "Evenk";
                    /*  9774 */ break;
                case 19:
                    /*  9776 */ name = "Ingush";
                    /*  9777 */ break;
                case 20:
                    /*  9779 */ name = "Irkutsk";
                    /*  9780 */ break;
                case 21:
                    /*  9782 */ name = "Ivanovo";
                    /*  9783 */ break;
                case 22:
                    /*  9785 */ name = "Kabardin-Balkar";
                    /*  9786 */ break;
                case 23:
                    /*  9788 */ name = "Kaliningrad";
                    /*  9789 */ break;
                case 24:
                    /*  9791 */ name = "Kalmyk";
                    /*  9792 */ break;
                case 25:
                    /*  9794 */ name = "Kaluga";
                    /*  9795 */ break;
                case 26:
                    /*  9797 */ name = "Kamchatka";
                    /*  9798 */ break;
                case 27:
                    /*  9800 */ name = "Karachay-Cherkess";
                    /*  9801 */ break;
                case 28:
                    /*  9803 */ name = "Karelia";
                    /*  9804 */ break;
                case 29:
                    /*  9806 */ name = "Kemerovo";
                    /*  9807 */ break;
                case 30:
                    /*  9809 */ name = "Khabarovsk";
                    /*  9810 */ break;
                case 31:
                    /*  9812 */ name = "Khakass";
                    /*  9813 */ break;
                case 32:
                    /*  9815 */ name = "Khanty-Mansiy";
                    /*  9816 */ break;
                case 33:
                    /*  9818 */ name = "Kirov";
                    /*  9819 */ break;
                case 34:
                    /*  9821 */ name = "Komi";
                    /*  9822 */ break;
                case 35:
                    /*  9824 */ name = "Komi-Permyak";
                    /*  9825 */ break;
                case 36:
                    /*  9827 */ name = "Koryak";
                    /*  9828 */ break;
                case 37:
                    /*  9830 */ name = "Kostroma";
                    /*  9831 */ break;
                case 38:
                    /*  9833 */ name = "Krasnodar";
                    /*  9834 */ break;
                case 39:
                    /*  9836 */ name = "Krasnoyarsk";
                    /*  9837 */ break;
                case 40:
                    /*  9839 */ name = "Kurgan";
                    /*  9840 */ break;
                case 41:
                    /*  9842 */ name = "Kursk";
                    /*  9843 */ break;
                case 42:
                    /*  9845 */ name = "Leningrad";
                    /*  9846 */ break;
                case 43:
                    /*  9848 */ name = "Lipetsk";
                    /*  9849 */ break;
                case 44:
                    /*  9851 */ name = "Magadan";
                    /*  9852 */ break;
                case 45:
                    /*  9854 */ name = "Mariy-El";
                    /*  9855 */ break;
                case 46:
                    /*  9857 */ name = "Mordovia";
                    /*  9858 */ break;
                case 47:
                    /*  9860 */ name = "Moskva";
                    /*  9861 */ break;
                case 48:
                    /*  9863 */ name = "Moscow City";
                    /*  9864 */ break;
                case 49:
                    /*  9866 */ name = "Murmansk";
                    /*  9867 */ break;
                case 50:
                    /*  9869 */ name = "Nenets";
                    /*  9870 */ break;
                case 51:
                    /*  9872 */ name = "Nizhegorod";
                    /*  9873 */ break;
                case 52:
                    /*  9875 */ name = "Novgorod";
                    /*  9876 */ break;
                case 53:
                    /*  9878 */ name = "Novosibirsk";
                    /*  9879 */ break;
                case 54:
                    /*  9881 */ name = "Omsk";
                    /*  9882 */ break;
                case 55:
                    /*  9884 */ name = "Orenburg";
                    /*  9885 */ break;
                case 56:
                    /*  9887 */ name = "Orel";
                    /*  9888 */ break;
                case 57:
                    /*  9890 */ name = "Penza";
                    /*  9891 */ break;
                case 58:
                    /*  9893 */ name = "Perm'";
                    /*  9894 */ break;
                case 59:
                    /*  9896 */ name = "Primor'ye";
                    /*  9897 */ break;
                case 60:
                    /*  9899 */ name = "Pskov";
                    /*  9900 */ break;
                case 61:
                    /*  9902 */ name = "Rostov";
                    /*  9903 */ break;
                case 62:
                    /*  9905 */ name = "Ryazan'";
                    /*  9906 */ break;
                case 63:
                    /*  9908 */ name = "Sakha";
                    /*  9909 */ break;
                case 64:
                    /*  9911 */ name = "Sakhalin";
                    /*  9912 */ break;
                case 65:
                    /*  9914 */ name = "Samara";
                    /*  9915 */ break;
                case 66:
                    /*  9917 */ name = "Saint Petersburg City";
                    /*  9918 */ break;
                case 67:
                    /*  9920 */ name = "Saratov";
                    /*  9921 */ break;
                case 68:
                    /*  9923 */ name = "North Ossetia";
                    /*  9924 */ break;
                case 69:
                    /*  9926 */ name = "Smolensk";
                    /*  9927 */ break;
                case 70:
                    /*  9929 */ name = "Stavropol'";
                    /*  9930 */ break;
                case 71:
                    /*  9932 */ name = "Sverdlovsk";
                    /*  9933 */ break;
                case 72:
                    /*  9935 */ name = "Tambovskaya oblast";
                    /*  9936 */ break;
                case 73:
                    /*  9938 */ name = "Tatarstan";
                    /*  9939 */ break;
                case 74:
                    /*  9941 */ name = "Taymyr";
                    /*  9942 */ break;
                case 75:
                    /*  9944 */ name = "Tomsk";
                    /*  9945 */ break;
                case 76:
                    /*  9947 */ name = "Tula";
                    /*  9948 */ break;
                case 77:
                    /*  9950 */ name = "Tver'";
                    /*  9951 */ break;
                case 78:
                    /*  9953 */ name = "Tyumen'";
                    /*  9954 */ break;
                case 79:
                    /*  9956 */ name = "Tuva";
                    /*  9957 */ break;
                case 80:
                    /*  9959 */ name = "Udmurt";
                    /*  9960 */ break;
                case 81:
                    /*  9962 */ name = "Ul'yanovsk";
                    /*  9963 */ break;
                case 82:
                    /*  9965 */ name = "Ust-Orda Buryat";
                    /*  9966 */ break;
                case 83:
                    /*  9968 */ name = "Vladimir";
                    /*  9969 */ break;
                case 84:
                    /*  9971 */ name = "Volgograd";
                    /*  9972 */ break;
                case 85:
                    /*  9974 */ name = "Vologda";
                    /*  9975 */ break;
                case 86:
                    /*  9977 */ name = "Voronezh";
                    /*  9978 */ break;
                case 87:
                    /*  9980 */ name = "Yamal-Nenets";
                    /*  9981 */ break;
                case 88:
                    /*  9983 */ name = "Yaroslavl'";
                    /*  9984 */ break;
                case 89:
                    /*  9986 */ name = "Yevrey";
                    /*  9987 */ break;
                case 90:
                    /*  9989 */ name = "Permskiy Kray";
                    /*  9990 */ break;
                case 91:
                    /*  9992 */ name = "Krasnoyarskiy Kray";
                    /*  9993 */ break;
                case 92:
                    /*  9995 */ name = "Kamchatskiy Kray";
                    /*  9996 */ break;
                case 93:
                    /*  9998 */ name = "Zabaykal'skiy Kray";
            }
        }

        /* 10002 */ if (country_code.equals("RW") == true) /* 10003 */ {
            switch (region_code2) {
                case 1:
                    /* 10005 */ name = "Butare";
                    /* 10006 */ break;
                case 6:
                    /* 10008 */ name = "Gitarama";
                    /* 10009 */ break;
                case 7:
                    /* 10011 */ name = "Kibungo";
                    /* 10012 */ break;
                case 9:
                    /* 10014 */ name = "Kigali";
                    /* 10015 */ break;
                case 11:
                    /* 10017 */ name = "Est";
                    /* 10018 */ break;
                case 12:
                    /* 10020 */ name = "Kigali";
                    /* 10021 */ break;
                case 13:
                    /* 10023 */ name = "Nord";
                    /* 10024 */ break;
                case 14:
                    /* 10026 */ name = "Ouest";
                    /* 10027 */ break;
                case 15:
                    /* 10029 */ name = "Sud";
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                /* 10033 */ case 10:
            }
        }
        if (country_code.equals("SA") == true) /* 10034 */ {
            switch (region_code2) {
                case 2:
                    /* 10036 */ name = "Al Bahah";
                    /* 10037 */ break;
                case 5:
                    /* 10039 */ name = "Al Madinah";
                    /* 10040 */ break;
                case 6:
                    /* 10042 */ name = "Ash Sharqiyah";
                    /* 10043 */ break;
                case 8:
                    /* 10045 */ name = "Al Qasim";
                    /* 10046 */ break;
                case 10:
                    /* 10048 */ name = "Ar Riyad";
                    /* 10049 */ break;
                case 11:
                    /* 10051 */ name = "Asir Province";
                    /* 10052 */ break;
                case 13:
                    /* 10054 */ name = "Ha'il";
                    /* 10055 */ break;
                case 14:
                    /* 10057 */ name = "Makkah";
                    /* 10058 */ break;
                case 15:
                    /* 10060 */ name = "Al Hudud ash Shamaliyah";
                    /* 10061 */ break;
                case 16:
                    /* 10063 */ name = "Najran";
                    /* 10064 */ break;
                case 17:
                    /* 10066 */ name = "Jizan";
                    /* 10067 */ break;
                case 19:
                    /* 10069 */ name = "Tabuk";
                    /* 10070 */ break;
                case 20:
                    /* 10072 */ name = "Al Jawf";
                case 3:
                case 4:
                case 7:
                case 9:
                case 12:
                /* 10076 */ case 18:
            }
        }
        if (country_code.equals("SB") == true) /* 10077 */ {
            switch (region_code2) {
                case 3:
                    /* 10079 */ name = "Malaita";
                    /* 10080 */ break;
                case 6:
                    /* 10082 */ name = "Guadalcanal";
                    /* 10083 */ break;
                case 7:
                    /* 10085 */ name = "Isabel";
                    /* 10086 */ break;
                case 8:
                    /* 10088 */ name = "Makira";
                    /* 10089 */ break;
                case 9:
                    /* 10091 */ name = "Temotu";
                    /* 10092 */ break;
                case 10:
                    /* 10094 */ name = "Central";
                    /* 10095 */ break;
                case 11:
                    /* 10097 */ name = "Western";
                    /* 10098 */ break;
                case 12:
                    /* 10100 */ name = "Choiseul";
                    /* 10101 */ break;
                case 13:
                    /* 10103 */ name = "Rennell and Bellona";
                case 4:
                case 5:
            }
        }
        /* 10107 */ if (country_code.equals("SC") == true) {
            /* 10108 */ switch (region_code2) {
                case 1:
                    /* 10110 */ name = "Anse aux Pins";
                    /* 10111 */ break;
                case 2:
                    /* 10113 */ name = "Anse Boileau";
                    /* 10114 */ break;
                case 3:
                    /* 10116 */ name = "Anse Etoile";
                    /* 10117 */ break;
                case 4:
                    /* 10119 */ name = "Anse Louis";
                    /* 10120 */ break;
                case 5:
                    /* 10122 */ name = "Anse Royale";
                    /* 10123 */ break;
                case 6:
                    /* 10125 */ name = "Baie Lazare";
                    /* 10126 */ break;
                case 7:
                    /* 10128 */ name = "Baie Sainte Anne";
                    /* 10129 */ break;
                case 8:
                    /* 10131 */ name = "Beau Vallon";
                    /* 10132 */ break;
                case 9:
                    /* 10134 */ name = "Bel Air";
                    /* 10135 */ break;
                case 10:
                    /* 10137 */ name = "Bel Ombre";
                    /* 10138 */ break;
                case 11:
                    /* 10140 */ name = "Cascade";
                    /* 10141 */ break;
                case 12:
                    /* 10143 */ name = "Glacis";
                    /* 10144 */ break;
                case 13:
                    /* 10146 */ name = "Grand' Anse";
                    /* 10147 */ break;
                case 14:
                    /* 10149 */ name = "Grand' Anse";
                    /* 10150 */ break;
                case 15:
                    /* 10152 */ name = "La Digue";
                    /* 10153 */ break;
                case 16:
                    /* 10155 */ name = "La Riviere Anglaise";
                    /* 10156 */ break;
                case 17:
                    /* 10158 */ name = "Mont Buxton";
                    /* 10159 */ break;
                case 18:
                    /* 10161 */ name = "Mont Fleuri";
                    /* 10162 */ break;
                case 19:
                    /* 10164 */ name = "Plaisance";
                    /* 10165 */ break;
                case 20:
                    /* 10167 */ name = "Pointe La Rue";
                    /* 10168 */ break;
                case 21:
                    /* 10170 */ name = "Port Glaud";
                    /* 10171 */ break;
                case 22:
                    /* 10173 */ name = "Saint Louis";
                    /* 10174 */ break;
                case 23:
                    /* 10176 */ name = "Takamaka";
            }
        }

        /* 10180 */ if (country_code.equals("SD") == true) /* 10181 */ {
            switch (region_code2) {
                case 27:
                    /* 10183 */ name = "Al Wusta";
                    /* 10184 */ break;
                case 28:
                    /* 10186 */ name = "Al Istiwa'iyah";
                    /* 10187 */ break;
                case 29:
                    /* 10189 */ name = "Al Khartum";
                    /* 10190 */ break;
                case 30:
                    /* 10192 */ name = "Ash Shamaliyah";
                    /* 10193 */ break;
                case 31:
                    /* 10195 */ name = "Ash Sharqiyah";
                    /* 10196 */ break;
                case 32:
                    /* 10198 */ name = "Bahr al Ghazal";
                    /* 10199 */ break;
                case 33:
                    /* 10201 */ name = "Darfur";
                    /* 10202 */ break;
                case 34:
                    /* 10204 */ name = "Kurdufan";
                    /* 10205 */ break;
                case 35:
                    /* 10207 */ name = "Upper Nile";
                    /* 10208 */ break;
                case 40:
                    /* 10210 */ name = "Al Wahadah State";
                    /* 10211 */ break;
                case 44:
                    /* 10213 */ name = "Central Equatoria State";
                case 36:
                case 37:
                case 38:
                case 39:
                case 41:
                case 42:
                /* 10217 */ case 43:
            }
        }
        if (country_code.equals("SE") == true) /* 10218 */ {
            switch (region_code2) {
                case 2:
                    /* 10220 */ name = "Blekinge Lan";
                    /* 10221 */ break;
                case 3:
                    /* 10223 */ name = "Gavleborgs Lan";
                    /* 10224 */ break;
                case 5:
                    /* 10226 */ name = "Gotlands Lan";
                    /* 10227 */ break;
                case 6:
                    /* 10229 */ name = "Hallands Lan";
                    /* 10230 */ break;
                case 7:
                    /* 10232 */ name = "Jamtlands Lan";
                    /* 10233 */ break;
                case 8:
                    /* 10235 */ name = "Jonkopings Lan";
                    /* 10236 */ break;
                case 9:
                    /* 10238 */ name = "Kalmar Lan";
                    /* 10239 */ break;
                case 10:
                    /* 10241 */ name = "Dalarnas Lan";
                    /* 10242 */ break;
                case 12:
                    /* 10244 */ name = "Kronobergs Lan";
                    /* 10245 */ break;
                case 14:
                    /* 10247 */ name = "Norrbottens Lan";
                    /* 10248 */ break;
                case 15:
                    /* 10250 */ name = "Orebro Lan";
                    /* 10251 */ break;
                case 16:
                    /* 10253 */ name = "Ostergotlands Lan";
                    /* 10254 */ break;
                case 18:
                    /* 10256 */ name = "Sodermanlands Lan";
                    /* 10257 */ break;
                case 21:
                    /* 10259 */ name = "Uppsala Lan";
                    /* 10260 */ break;
                case 22:
                    /* 10262 */ name = "Varmlands Lan";
                    /* 10263 */ break;
                case 23:
                    /* 10265 */ name = "Vasterbottens Lan";
                    /* 10266 */ break;
                case 24:
                    /* 10268 */ name = "Vasternorrlands Lan";
                    /* 10269 */ break;
                case 25:
                    /* 10271 */ name = "Vastmanlands Lan";
                    /* 10272 */ break;
                case 26:
                    /* 10274 */ name = "Stockholms Lan";
                    /* 10275 */ break;
                case 27:
                    /* 10277 */ name = "Skane Lan";
                    /* 10278 */ break;
                case 28:
                    /* 10280 */ name = "Vastra Gotaland";
                case 4:
                case 11:
                case 13:
                case 17:
                case 19:
                /* 10284 */ case 20:
            }
        }
        if (country_code.equals("SH") == true) {
            /* 10285 */ switch (region_code2) {
                case 1:
                    /* 10287 */ name = "Ascension";
                    /* 10288 */ break;
                case 2:
                    /* 10290 */ name = "Saint Helena";
                    /* 10291 */ break;
                case 3:
                    /* 10293 */ name = "Tristan da Cunha";
            }
        }

        /* 10297 */ if (country_code.equals("SI") == true) {
            /* 10298 */ switch (region_code2) {
                case 1:
                    /* 10300 */ name = "Ajdovscina";
                    /* 10301 */ break;
                case 2:
                    /* 10303 */ name = "Beltinci";
                    /* 10304 */ break;
                case 3:
                    /* 10306 */ name = "Bled";
                    /* 10307 */ break;
                case 4:
                    /* 10309 */ name = "Bohinj";
                    /* 10310 */ break;
                case 5:
                    /* 10312 */ name = "Borovnica";
                    /* 10313 */ break;
                case 6:
                    /* 10315 */ name = "Bovec";
                    /* 10316 */ break;
                case 7:
                    /* 10318 */ name = "Brda";
                    /* 10319 */ break;
                case 8:
                    /* 10321 */ name = "Brezice";
                    /* 10322 */ break;
                case 9:
                    /* 10324 */ name = "Brezovica";
                    /* 10325 */ break;
                case 11:
                    /* 10327 */ name = "Celje";
                    /* 10328 */ break;
                case 12:
                    /* 10330 */ name = "Cerklje na Gorenjskem";
                    /* 10331 */ break;
                case 13:
                    /* 10333 */ name = "Cerknica";
                    /* 10334 */ break;
                case 14:
                    /* 10336 */ name = "Cerkno";
                    /* 10337 */ break;
                case 15:
                    /* 10339 */ name = "Crensovci";
                    /* 10340 */ break;
                case 16:
                    /* 10342 */ name = "Crna na Koroskem";
                    /* 10343 */ break;
                case 17:
                    /* 10345 */ name = "Crnomelj";
                    /* 10346 */ break;
                case 19:
                    /* 10348 */ name = "Divaca";
                    /* 10349 */ break;
                case 20:
                    /* 10351 */ name = "Dobrepolje";
                    /* 10352 */ break;
                case 22:
                    /* 10354 */ name = "Dol pri Ljubljani";
                    /* 10355 */ break;
                case 24:
                    /* 10357 */ name = "Dornava";
                    /* 10358 */ break;
                case 25:
                    /* 10360 */ name = "Dravograd";
                    /* 10361 */ break;
                case 26:
                    /* 10363 */ name = "Duplek";
                    /* 10364 */ break;
                case 27:
                    /* 10366 */ name = "Gorenja Vas-Poljane";
                    /* 10367 */ break;
                case 28:
                    /* 10369 */ name = "Gorisnica";
                    /* 10370 */ break;
                case 29:
                    /* 10372 */ name = "Gornja Radgona";
                    /* 10373 */ break;
                case 30:
                    /* 10375 */ name = "Gornji Grad";
                    /* 10376 */ break;
                case 31:
                    /* 10378 */ name = "Gornji Petrovci";
                    /* 10379 */ break;
                case 32:
                    /* 10381 */ name = "Grosuplje";
                    /* 10382 */ break;
                case 34:
                    /* 10384 */ name = "Hrastnik";
                    /* 10385 */ break;
                case 35:
                    /* 10387 */ name = "Hrpelje-Kozina";
                    /* 10388 */ break;
                case 36:
                    /* 10390 */ name = "Idrija";
                    /* 10391 */ break;
                case 37:
                    /* 10393 */ name = "Ig";
                    /* 10394 */ break;
                case 38:
                    /* 10396 */ name = "Ilirska Bistrica";
                    /* 10397 */ break;
                case 39:
                    /* 10399 */ name = "Ivancna Gorica";
                    /* 10400 */ break;
                case 40:
                    /* 10402 */ name = "Izola-Isola";
                    /* 10403 */ break;
                case 42:
                    /* 10405 */ name = "Jursinci";
                    /* 10406 */ break;
                case 44:
                    /* 10408 */ name = "Kanal";
                    /* 10409 */ break;
                case 45:
                    /* 10411 */ name = "Kidricevo";
                    /* 10412 */ break;
                case 46:
                    /* 10414 */ name = "Kobarid";
                    /* 10415 */ break;
                case 47:
                    /* 10417 */ name = "Kobilje";
                    /* 10418 */ break;
                case 49:
                    /* 10420 */ name = "Komen";
                    /* 10421 */ break;
                case 50:
                    /* 10423 */ name = "Koper-Capodistria";
                    /* 10424 */ break;
                case 51:
                    /* 10426 */ name = "Kozje";
                    /* 10427 */ break;
                case 52:
                    /* 10429 */ name = "Kranj";
                    /* 10430 */ break;
                case 53:
                    /* 10432 */ name = "Kranjska Gora";
                    /* 10433 */ break;
                case 54:
                    /* 10435 */ name = "Krsko";
                    /* 10436 */ break;
                case 55:
                    /* 10438 */ name = "Kungota";
                    /* 10439 */ break;
                case 57:
                    /* 10441 */ name = "Lasko";
                    /* 10442 */ break;
                case 61:
                    /* 10444 */ name = "Ljubljana";
                    /* 10445 */ break;
                case 62:
                    /* 10447 */ name = "Ljubno";
                    /* 10448 */ break;
                case 64:
                    /* 10450 */ name = "Logatec";
                    /* 10451 */ break;
                case 66:
                    /* 10453 */ name = "Loski Potok";
                    /* 10454 */ break;
                case 68:
                    /* 10456 */ name = "Lukovica";
                    /* 10457 */ break;
                case 71:
                    /* 10459 */ name = "Medvode";
                    /* 10460 */ break;
                case 72:
                    /* 10462 */ name = "Menges";
                    /* 10463 */ break;
                case 73:
                    /* 10465 */ name = "Metlika";
                    /* 10466 */ break;
                case 74:
                    /* 10468 */ name = "Mezica";
                    /* 10469 */ break;
                case 76:
                    /* 10471 */ name = "Mislinja";
                    /* 10472 */ break;
                case 77:
                    /* 10474 */ name = "Moravce";
                    /* 10475 */ break;
                case 78:
                    /* 10477 */ name = "Moravske Toplice";
                    /* 10478 */ break;
                case 79:
                    /* 10480 */ name = "Mozirje";
                    /* 10481 */ break;
                case 80:
                    /* 10483 */ name = "Murska Sobota";
                    /* 10484 */ break;
                case 81:
                    /* 10486 */ name = "Muta";
                    /* 10487 */ break;
                case 82:
                    /* 10489 */ name = "Naklo";
                    /* 10490 */ break;
                case 83:
                    /* 10492 */ name = "Nazarje";
                    /* 10493 */ break;
                case 84:
                    /* 10495 */ name = "Nova Gorica";
                    /* 10496 */ break;
                case 86:
                    /* 10498 */ name = "Odranci";
                    /* 10499 */ break;
                case 87:
                    /* 10501 */ name = "Ormoz";
                    /* 10502 */ break;
                case 88:
                    /* 10504 */ name = "Osilnica";
                    /* 10505 */ break;
                case 89:
                    /* 10507 */ name = "Pesnica";
                    /* 10508 */ break;
                case 91:
                    /* 10510 */ name = "Pivka";
                    /* 10511 */ break;
                case 92:
                    /* 10513 */ name = "Podcetrtek";
                    /* 10514 */ break;
                case 94:
                    /* 10516 */ name = "Postojna";
                    /* 10517 */ break;
                case 97:
                    /* 10519 */ name = "Puconci";
                    /* 10520 */ break;
                case 98:
                    /* 10522 */ name = "Racam";
                    /* 10523 */ break;
                case 99:
                    /* 10525 */ name = "Radece";
                    /* 10526 */ break;
                case 832:
                    /* 10528 */ name = "Radenci";
                    /* 10529 */ break;
                case 833:
                    /* 10531 */ name = "Radlje ob Dravi";
                    /* 10532 */ break;
                case 834:
                    /* 10534 */ name = "Radovljica";
                    /* 10535 */ break;
                case 837:
                    /* 10537 */ name = "Rogasovci";
                    /* 10538 */ break;
                case 838:
                    /* 10540 */ name = "Rogaska Slatina";
                    /* 10541 */ break;
                case 839:
                    /* 10543 */ name = "Rogatec";
                    /* 10544 */ break;
                case 875:
                    /* 10546 */ name = "Semic";
                    /* 10547 */ break;
                case 876:
                    /* 10549 */ name = "Sencur";
                    /* 10550 */ break;
                case 877:
                    /* 10552 */ name = "Sentilj";
                    /* 10553 */ break;
                case 878:
                    /* 10555 */ name = "Sentjernej";
                    /* 10556 */ break;
                case 880:
                    /* 10558 */ name = "Sevnica";
                    /* 10559 */ break;
                case 881:
                    /* 10561 */ name = "Sezana";
                    /* 10562 */ break;
                case 882:
                    /* 10564 */ name = "Skocjan";
                    /* 10565 */ break;
                case 883:
                    /* 10567 */ name = "Skofja Loka";
                    /* 10568 */ break;
                case 918:
                    /* 10570 */ name = "Skofljica";
                    /* 10571 */ break;
                case 919:
                    /* 10573 */ name = "Slovenj Gradec";
                    /* 10574 */ break;
                case 921:
                    /* 10576 */ name = "Slovenske Konjice";
                    /* 10577 */ break;
                case 922:
                    /* 10579 */ name = "Smarje pri Jelsah";
                    /* 10580 */ break;
                case 923:
                    /* 10582 */ name = "Smartno ob Paki";
                    /* 10583 */ break;
                case 924:
                    /* 10585 */ name = "Sostanj";
                    /* 10586 */ break;
                case 925:
                    /* 10588 */ name = "Starse";
                    /* 10589 */ break;
                case 926:
                    /* 10591 */ name = "Store";
                    /* 10592 */ break;
                case 961:
                    /* 10594 */ name = "Sveti Jurij";
                    /* 10595 */ break;
                case 962:
                    /* 10597 */ name = "Tolmin";
                    /* 10598 */ break;
                case 963:
                    /* 10600 */ name = "Trbovlje";
                    /* 10601 */ break;
                case 964:
                    /* 10603 */ name = "Trebnje";
                    /* 10604 */ break;
                case 965:
                    /* 10606 */ name = "Trzic";
                    /* 10607 */ break;
                case 966:
                    /* 10609 */ name = "Turnisce";
                    /* 10610 */ break;
                case 967:
                    /* 10612 */ name = "Velenje";
                    /* 10613 */ break;
                case 968:
                    /* 10615 */ name = "Velike Lasce";
                    /* 10616 */ break;
                case 1004:
                    /* 10618 */ name = "Vipava";
                    /* 10619 */ break;
                case 1005:
                    /* 10621 */ name = "Vitanje";
                    /* 10622 */ break;
                case 1006:
                    /* 10624 */ name = "Vodice";
                    /* 10625 */ break;
                case 1008:
                    /* 10627 */ name = "Vrhnika";
                    /* 10628 */ break;
                case 1009:
                    /* 10630 */ name = "Vuzenica";
                    /* 10631 */ break;
                case 1010:
                    /* 10633 */ name = "Zagorje ob Savi";
                    /* 10634 */ break;
                case 1012:
                    /* 10636 */ name = "Zavrc";
                    /* 10637 */ break;
                case 1047:
                    /* 10639 */ name = "Zelezniki";
                    /* 10640 */ break;
                case 1048:
                    /* 10642 */ name = "Ziri";
                    /* 10643 */ break;
                case 1049:
                    /* 10645 */ name = "Zrece";
                    /* 10646 */ break;
                case 1093:
                    /* 10648 */ name = "Dobrova-Horjul-Polhov Gradec";
                    /* 10649 */ break;
                case 1096:
                    /* 10651 */ name = "Domzale";
                    /* 10652 */ break;
                case 1136:
                    /* 10654 */ name = "Jesenice";
                    /* 10655 */ break;
                case 1138:
                    /* 10657 */ name = "Kamnik";
                    /* 10658 */ break;
                case 1139:
                    /* 10660 */ name = "Kocevje";
                    /* 10661 */ break;
                case 1177:
                    /* 10663 */ name = "Kuzma";
                    /* 10664 */ break;
                case 1178:
                    /* 10666 */ name = "Lenart";
                    /* 10667 */ break;
                case 1180:
                    /* 10669 */ name = "Litija";
                    /* 10670 */ break;
                case 1181:
                    /* 10672 */ name = "Ljutomer";
                    /* 10673 */ break;
                case 1182:
                    /* 10675 */ name = "Loska Dolina";
                    /* 10676 */ break;
                case 1184:
                    /* 10678 */ name = "Luce";
                    /* 10679 */ break;
                case 1219:
                    /* 10681 */ name = "Majsperk";
                    /* 10682 */ break;
                case 1220:
                    /* 10684 */ name = "Maribor";
                    /* 10685 */ break;
                case 1223:
                    /* 10687 */ name = "Miren-Kostanjevica";
                    /* 10688 */ break;
                case 1225:
                    /* 10690 */ name = "Novo Mesto";
                    /* 10691 */ break;
                case 1227:
                    /* 10693 */ name = "Piran";
                    /* 10694 */ break;
                case 1266:
                    /* 10696 */ name = "Preddvor";
                    /* 10697 */ break;
                case 1268:
                    /* 10699 */ name = "Ptuj";
                    /* 10700 */ break;
                case 1305:
                    /* 10702 */ name = "Ribnica";
                    /* 10703 */ break;
                case 1307:
                    /* 10705 */ name = "Ruse";
                    /* 10706 */ break;
                case 1311:
                    /* 10708 */ name = "Sentjur pri Celju";
                    /* 10709 */ break;
                case 1312:
                    /* 10711 */ name = "Slovenska Bistrica";
                    /* 10712 */ break;
                case 1392:
                    /* 10714 */ name = "Videm";
                    /* 10715 */ break;
                case 1393:
                    /* 10717 */ name = "Vojnik";
                    /* 10718 */ break;
                case 1395:
                    /* 10720 */ name = "Zalec";
            }
        }

        /* 10724 */ if (country_code.equals("SK") == true) {
            /* 10725 */ switch (region_code2) {
                case 1:
                    /* 10727 */ name = "Banska Bystrica";
                    /* 10728 */ break;
                case 2:
                    /* 10730 */ name = "Bratislava";
                    /* 10731 */ break;
                case 3:
                    /* 10733 */ name = "Kosice";
                    /* 10734 */ break;
                case 4:
                    /* 10736 */ name = "Nitra";
                    /* 10737 */ break;
                case 5:
                    /* 10739 */ name = "Presov";
                    /* 10740 */ break;
                case 6:
                    /* 10742 */ name = "Trencin";
                    /* 10743 */ break;
                case 7:
                    /* 10745 */ name = "Trnava";
                    /* 10746 */ break;
                case 8:
                    /* 10748 */ name = "Zilina";
            }
        }

        /* 10752 */ if (country_code.equals("SL") == true) {
            /* 10753 */ switch (region_code2) {
                case 1:
                    /* 10755 */ name = "Eastern";
                    /* 10756 */ break;
                case 2:
                    /* 10758 */ name = "Northern";
                    /* 10759 */ break;
                case 3:
                    /* 10761 */ name = "Southern";
                    /* 10762 */ break;
                case 4:
                    /* 10764 */ name = "Western Area";
            }
        }

        /* 10768 */ if (country_code.equals("SM") == true) {
            /* 10769 */ switch (region_code2) {
                case 1:
                    /* 10771 */ name = "Acquaviva";
                    /* 10772 */ break;
                case 2:
                    /* 10774 */ name = "Chiesanuova";
                    /* 10775 */ break;
                case 3:
                    /* 10777 */ name = "Domagnano";
                    /* 10778 */ break;
                case 4:
                    /* 10780 */ name = "Faetano";
                    /* 10781 */ break;
                case 5:
                    /* 10783 */ name = "Fiorentino";
                    /* 10784 */ break;
                case 6:
                    /* 10786 */ name = "Borgo Maggiore";
                    /* 10787 */ break;
                case 7:
                    /* 10789 */ name = "San Marino";
                    /* 10790 */ break;
                case 8:
                    /* 10792 */ name = "Monte Giardino";
                    /* 10793 */ break;
                case 9:
                    /* 10795 */ name = "Serravalle";
            }
        }

        /* 10799 */ if (country_code.equals("SN") == true) /* 10800 */ {
            switch (region_code2) {
                case 1:
                    /* 10802 */ name = "Dakar";
                    /* 10803 */ break;
                case 3:
                    /* 10805 */ name = "Diourbel";
                    /* 10806 */ break;
                case 5:
                    /* 10808 */ name = "Tambacounda";
                    /* 10809 */ break;
                case 7:
                    /* 10811 */ name = "Thies";
                    /* 10812 */ break;
                case 9:
                    /* 10814 */ name = "Fatick";
                    /* 10815 */ break;
                case 10:
                    /* 10817 */ name = "Kaolack";
                    /* 10818 */ break;
                case 11:
                    /* 10820 */ name = "Kolda";
                    /* 10821 */ break;
                case 12:
                    /* 10823 */ name = "Ziguinchor";
                    /* 10824 */ break;
                case 13:
                    /* 10826 */ name = "Louga";
                    /* 10827 */ break;
                case 14:
                    /* 10829 */ name = "Saint-Louis";
                    /* 10830 */ break;
                case 15:
                    /* 10832 */ name = "Matam";
                case 2:
                case 4:
                case 6:
                /* 10836 */ case 8:
            }
        }
        if (country_code.equals("SO") == true) /* 10837 */ {
            switch (region_code2) {
                case 1:
                    /* 10839 */ name = "Bakool";
                    /* 10840 */ break;
                case 2:
                    /* 10842 */ name = "Banaadir";
                    /* 10843 */ break;
                case 3:
                    /* 10845 */ name = "Bari";
                    /* 10846 */ break;
                case 4:
                    /* 10848 */ name = "Bay";
                    /* 10849 */ break;
                case 5:
                    /* 10851 */ name = "Galguduud";
                    /* 10852 */ break;
                case 6:
                    /* 10854 */ name = "Gedo";
                    /* 10855 */ break;
                case 7:
                    /* 10857 */ name = "Hiiraan";
                    /* 10858 */ break;
                case 8:
                    /* 10860 */ name = "Jubbada Dhexe";
                    /* 10861 */ break;
                case 9:
                    /* 10863 */ name = "Jubbada Hoose";
                    /* 10864 */ break;
                case 10:
                    /* 10866 */ name = "Mudug";
                    /* 10867 */ break;
                case 11:
                    /* 10869 */ name = "Nugaal";
                    /* 10870 */ break;
                case 12:
                    /* 10872 */ name = "Sanaag";
                    /* 10873 */ break;
                case 13:
                    /* 10875 */ name = "Shabeellaha Dhexe";
                    /* 10876 */ break;
                case 14:
                    /* 10878 */ name = "Shabeellaha Hoose";
                    /* 10879 */ break;
                case 16:
                    /* 10881 */ name = "Woqooyi Galbeed";
                    /* 10882 */ break;
                case 18:
                    /* 10884 */ name = "Nugaal";
                    /* 10885 */ break;
                case 19:
                    /* 10887 */ name = "Togdheer";
                    /* 10888 */ break;
                case 20:
                    /* 10890 */ name = "Woqooyi Galbeed";
                    /* 10891 */ break;
                case 21:
                    /* 10893 */ name = "Awdal";
                    /* 10894 */ break;
                case 22:
                    /* 10896 */ name = "Sool";
                case 15:
                case 17:
            }
        }
        /* 10900 */ if (country_code.equals("SR") == true) {
            /* 10901 */ switch (region_code2) {
                case 10:
                    /* 10903 */ name = "Brokopondo";
                    /* 10904 */ break;
                case 11:
                    /* 10906 */ name = "Commewijne";
                    /* 10907 */ break;
                case 12:
                    /* 10909 */ name = "Coronie";
                    /* 10910 */ break;
                case 13:
                    /* 10912 */ name = "Marowijne";
                    /* 10913 */ break;
                case 14:
                    /* 10915 */ name = "Nickerie";
                    /* 10916 */ break;
                case 15:
                    /* 10918 */ name = "Para";
                    /* 10919 */ break;
                case 16:
                    /* 10921 */ name = "Paramaribo";
                    /* 10922 */ break;
                case 17:
                    /* 10924 */ name = "Saramacca";
                    /* 10925 */ break;
                case 18:
                    /* 10927 */ name = "Sipaliwini";
                    /* 10928 */ break;
                case 19:
                    /* 10930 */ name = "Wanica";
            }
        }

        /* 10934 */ if (country_code.equals("ST") == true) {
            /* 10935 */ switch (region_code2) {
                case 1:
                    /* 10937 */ name = "Principe";
                    /* 10938 */ break;
                case 2:
                    /* 10940 */ name = "Sao Tome";
            }
        }

        /* 10944 */ if (country_code.equals("SV") == true) {
            /* 10945 */ switch (region_code2) {
                case 1:
                    /* 10947 */ name = "Ahuachapan";
                    /* 10948 */ break;
                case 2:
                    /* 10950 */ name = "Cabanas";
                    /* 10951 */ break;
                case 3:
                    /* 10953 */ name = "Chalatenango";
                    /* 10954 */ break;
                case 4:
                    /* 10956 */ name = "Cuscatlan";
                    /* 10957 */ break;
                case 5:
                    /* 10959 */ name = "La Libertad";
                    /* 10960 */ break;
                case 6:
                    /* 10962 */ name = "La Paz";
                    /* 10963 */ break;
                case 7:
                    /* 10965 */ name = "La Union";
                    /* 10966 */ break;
                case 8:
                    /* 10968 */ name = "Morazan";
                    /* 10969 */ break;
                case 9:
                    /* 10971 */ name = "San Miguel";
                    /* 10972 */ break;
                case 10:
                    /* 10974 */ name = "San Salvador";
                    /* 10975 */ break;
                case 11:
                    /* 10977 */ name = "Santa Ana";
                    /* 10978 */ break;
                case 12:
                    /* 10980 */ name = "San Vicente";
                    /* 10981 */ break;
                case 13:
                    /* 10983 */ name = "Sonsonate";
                    /* 10984 */ break;
                case 14:
                    /* 10986 */ name = "Usulutan";
            }
        }

        /* 10990 */ if (country_code.equals("SY") == true) {
            /* 10991 */ switch (region_code2) {
                case 1:
                    /* 10993 */ name = "Al Hasakah";
                    /* 10994 */ break;
                case 2:
                    /* 10996 */ name = "Al Ladhiqiyah";
                    /* 10997 */ break;
                case 3:
                    /* 10999 */ name = "Al Qunaytirah";
                    /* 11000 */ break;
                case 4:
                    /* 11002 */ name = "Ar Raqqah";
                    /* 11003 */ break;
                case 5:
                    /* 11005 */ name = "As Suwayda'";
                    /* 11006 */ break;
                case 6:
                    /* 11008 */ name = "Dar";
                    /* 11009 */ break;
                case 7:
                    /* 11011 */ name = "Dayr az Zawr";
                    /* 11012 */ break;
                case 8:
                    /* 11014 */ name = "Rif Dimashq";
                    /* 11015 */ break;
                case 9:
                    /* 11017 */ name = "Halab";
                    /* 11018 */ break;
                case 10:
                    /* 11020 */ name = "Hamah";
                    /* 11021 */ break;
                case 11:
                    /* 11023 */ name = "Hims";
                    /* 11024 */ break;
                case 12:
                    /* 11026 */ name = "Idlib";
                    /* 11027 */ break;
                case 13:
                    /* 11029 */ name = "Dimashq";
                    /* 11030 */ break;
                case 14:
                    /* 11032 */ name = "Tartus";
            }
        }

        /* 11036 */ if (country_code.equals("SZ") == true) {
            /* 11037 */ switch (region_code2) {
                case 1:
                    /* 11039 */ name = "Hhohho";
                    /* 11040 */ break;
                case 2:
                    /* 11042 */ name = "Lubombo";
                    /* 11043 */ break;
                case 3:
                    /* 11045 */ name = "Manzini";
                    /* 11046 */ break;
                case 4:
                    /* 11048 */ name = "Shiselweni";
                    /* 11049 */ break;
                case 5:
                    /* 11051 */ name = "Praslin";
            }
        }

        /* 11055 */ if (country_code.equals("TD") == true) {
            /* 11056 */ switch (region_code2) {
                case 1:
                    /* 11058 */ name = "Batha";
                    /* 11059 */ break;
                case 2:
                    /* 11061 */ name = "Biltine";
                    /* 11062 */ break;
                case 3:
                    /* 11064 */ name = "Borkou-Ennedi-Tibesti";
                    /* 11065 */ break;
                case 4:
                    /* 11067 */ name = "Chari-Baguirmi";
                    /* 11068 */ break;
                case 5:
                    /* 11070 */ name = "Guera";
                    /* 11071 */ break;
                case 6:
                    /* 11073 */ name = "Kanem";
                    /* 11074 */ break;
                case 7:
                    /* 11076 */ name = "Lac";
                    /* 11077 */ break;
                case 8:
                    /* 11079 */ name = "Logone Occidental";
                    /* 11080 */ break;
                case 9:
                    /* 11082 */ name = "Logone Oriental";
                    /* 11083 */ break;
                case 10:
                    /* 11085 */ name = "Mayo-Kebbi";
                    /* 11086 */ break;
                case 11:
                    /* 11088 */ name = "Moyen-Chari";
                    /* 11089 */ break;
                case 12:
                    /* 11091 */ name = "Ouaddai";
                    /* 11092 */ break;
                case 13:
                    /* 11094 */ name = "Salamat";
                    /* 11095 */ break;
                case 14:
                    /* 11097 */ name = "Tandjile";
            }
        }

        /* 11101 */ if (country_code.equals("TG") == true) {
            /* 11102 */ switch (region_code2) {
                case 22:
                    /* 11104 */ name = "Centrale";
                    /* 11105 */ break;
                case 23:
                    /* 11107 */ name = "Kara";
                    /* 11108 */ break;
                case 24:
                    /* 11110 */ name = "Maritime";
                    /* 11111 */ break;
                case 25:
                    /* 11113 */ name = "Plateaux";
                    /* 11114 */ break;
                case 26:
                    /* 11116 */ name = "Savanes";
            }
        }

        /* 11120 */ if (country_code.equals("TH") == true) /* 11121 */ {
            switch (region_code2) {
                case 1:
                    /* 11123 */ name = "Mae Hong Son";
                    /* 11124 */ break;
                case 2:
                    /* 11126 */ name = "Chiang Mai";
                    /* 11127 */ break;
                case 3:
                    /* 11129 */ name = "Chiang Rai";
                    /* 11130 */ break;
                case 4:
                    /* 11132 */ name = "Nan";
                    /* 11133 */ break;
                case 5:
                    /* 11135 */ name = "Lamphun";
                    /* 11136 */ break;
                case 6:
                    /* 11138 */ name = "Lampang";
                    /* 11139 */ break;
                case 7:
                    /* 11141 */ name = "Phrae";
                    /* 11142 */ break;
                case 8:
                    /* 11144 */ name = "Tak";
                    /* 11145 */ break;
                case 9:
                    /* 11147 */ name = "Sukhothai";
                    /* 11148 */ break;
                case 10:
                    /* 11150 */ name = "Uttaradit";
                    /* 11151 */ break;
                case 11:
                    /* 11153 */ name = "Kamphaeng Phet";
                    /* 11154 */ break;
                case 12:
                    /* 11156 */ name = "Phitsanulok";
                    /* 11157 */ break;
                case 13:
                    /* 11159 */ name = "Phichit";
                    /* 11160 */ break;
                case 14:
                    /* 11162 */ name = "Phetchabun";
                    /* 11163 */ break;
                case 15:
                    /* 11165 */ name = "Uthai Thani";
                    /* 11166 */ break;
                case 16:
                    /* 11168 */ name = "Nakhon Sawan";
                    /* 11169 */ break;
                case 17:
                    /* 11171 */ name = "Nong Khai";
                    /* 11172 */ break;
                case 18:
                    /* 11174 */ name = "Loei";
                    /* 11175 */ break;
                case 20:
                    /* 11177 */ name = "Sakon Nakhon";
                    /* 11178 */ break;
                case 21:
                    /* 11180 */ name = "Nakhon Phanom";
                    /* 11181 */ break;
                case 22:
                    /* 11183 */ name = "Khon Kaen";
                    /* 11184 */ break;
                case 23:
                    /* 11186 */ name = "Kalasin";
                    /* 11187 */ break;
                case 24:
                    /* 11189 */ name = "Maha Sarakham";
                    /* 11190 */ break;
                case 25:
                    /* 11192 */ name = "Roi Et";
                    /* 11193 */ break;
                case 26:
                    /* 11195 */ name = "Chaiyaphum";
                    /* 11196 */ break;
                case 27:
                    /* 11198 */ name = "Nakhon Ratchasima";
                    /* 11199 */ break;
                case 28:
                    /* 11201 */ name = "Buriram";
                    /* 11202 */ break;
                case 29:
                    /* 11204 */ name = "Surin";
                    /* 11205 */ break;
                case 30:
                    /* 11207 */ name = "Sisaket";
                    /* 11208 */ break;
                case 31:
                    /* 11210 */ name = "Narathiwat";
                    /* 11211 */ break;
                case 32:
                    /* 11213 */ name = "Chai Nat";
                    /* 11214 */ break;
                case 33:
                    /* 11216 */ name = "Sing Buri";
                    /* 11217 */ break;
                case 34:
                    /* 11219 */ name = "Lop Buri";
                    /* 11220 */ break;
                case 35:
                    /* 11222 */ name = "Ang Thong";
                    /* 11223 */ break;
                case 36:
                    /* 11225 */ name = "Phra Nakhon Si Ayutthaya";
                    /* 11226 */ break;
                case 37:
                    /* 11228 */ name = "Saraburi";
                    /* 11229 */ break;
                case 38:
                    /* 11231 */ name = "Nonthaburi";
                    /* 11232 */ break;
                case 39:
                    /* 11234 */ name = "Pathum Thani";
                    /* 11235 */ break;
                case 40:
                    /* 11237 */ name = "Krung Thep";
                    /* 11238 */ break;
                case 41:
                    /* 11240 */ name = "Phayao";
                    /* 11241 */ break;
                case 42:
                    /* 11243 */ name = "Samut Prakan";
                    /* 11244 */ break;
                case 43:
                    /* 11246 */ name = "Nakhon Nayok";
                    /* 11247 */ break;
                case 44:
                    /* 11249 */ name = "Chachoengsao";
                    /* 11250 */ break;
                case 45:
                    /* 11252 */ name = "Prachin Buri";
                    /* 11253 */ break;
                case 46:
                    /* 11255 */ name = "Chon Buri";
                    /* 11256 */ break;
                case 47:
                    /* 11258 */ name = "Rayong";
                    /* 11259 */ break;
                case 48:
                    /* 11261 */ name = "Chanthaburi";
                    /* 11262 */ break;
                case 49:
                    /* 11264 */ name = "Trat";
                    /* 11265 */ break;
                case 50:
                    /* 11267 */ name = "Kanchanaburi";
                    /* 11268 */ break;
                case 51:
                    /* 11270 */ name = "Suphan Buri";
                    /* 11271 */ break;
                case 52:
                    /* 11273 */ name = "Ratchaburi";
                    /* 11274 */ break;
                case 53:
                    /* 11276 */ name = "Nakhon Pathom";
                    /* 11277 */ break;
                case 54:
                    /* 11279 */ name = "Samut Songkhram";
                    /* 11280 */ break;
                case 55:
                    /* 11282 */ name = "Samut Sakhon";
                    /* 11283 */ break;
                case 56:
                    /* 11285 */ name = "Phetchaburi";
                    /* 11286 */ break;
                case 57:
                    /* 11288 */ name = "Prachuap Khiri Khan";
                    /* 11289 */ break;
                case 58:
                    /* 11291 */ name = "Chumphon";
                    /* 11292 */ break;
                case 59:
                    /* 11294 */ name = "Ranong";
                    /* 11295 */ break;
                case 60:
                    /* 11297 */ name = "Surat Thani";
                    /* 11298 */ break;
                case 61:
                    /* 11300 */ name = "Phangnga";
                    /* 11301 */ break;
                case 62:
                    /* 11303 */ name = "Phuket";
                    /* 11304 */ break;
                case 63:
                    /* 11306 */ name = "Krabi";
                    /* 11307 */ break;
                case 64:
                    /* 11309 */ name = "Nakhon Si Thammarat";
                    /* 11310 */ break;
                case 65:
                    /* 11312 */ name = "Trang";
                    /* 11313 */ break;
                case 66:
                    /* 11315 */ name = "Phatthalung";
                    /* 11316 */ break;
                case 67:
                    /* 11318 */ name = "Satun";
                    /* 11319 */ break;
                case 68:
                    /* 11321 */ name = "Songkhla";
                    /* 11322 */ break;
                case 69:
                    /* 11324 */ name = "Pattani";
                    /* 11325 */ break;
                case 70:
                    /* 11327 */ name = "Yala";
                    /* 11328 */ break;
                case 71:
                    /* 11330 */ name = "Ubon Ratchathani";
                    /* 11331 */ break;
                case 72:
                    /* 11333 */ name = "Yasothon";
                    /* 11334 */ break;
                case 73:
                    /* 11336 */ name = "Nakhon Phanom";
                    /* 11337 */ break;
                case 75:
                    /* 11339 */ name = "Ubon Ratchathani";
                    /* 11340 */ break;
                case 76:
                    /* 11342 */ name = "Udon Thani";
                    /* 11343 */ break;
                case 77:
                    /* 11345 */ name = "Amnat Charoen";
                    /* 11346 */ break;
                case 78:
                    /* 11348 */ name = "Mukdahan";
                    /* 11349 */ break;
                case 79:
                    /* 11351 */ name = "Nong Bua Lamphu";
                    /* 11352 */ break;
                case 80:
                    /* 11354 */ name = "Sa Kaeo";
                case 19:
                case 74:
            }
        }
        /* 11358 */ if (country_code.equals("TJ") == true) {
            /* 11359 */ switch (region_code2) {
                case 1:
                    /* 11361 */ name = "Kuhistoni Badakhshon";
                    /* 11362 */ break;
                case 2:
                    /* 11364 */ name = "Khatlon";
                    /* 11365 */ break;
                case 3:
                    /* 11367 */ name = "Sughd";
            }
        }

        /* 11371 */ if (country_code.equals("TM") == true) {
            /* 11372 */ switch (region_code2) {
                case 1:
                    /* 11374 */ name = "Ahal";
                    /* 11375 */ break;
                case 2:
                    /* 11377 */ name = "Balkan";
                    /* 11378 */ break;
                case 3:
                    /* 11380 */ name = "Dashoguz";
                    /* 11381 */ break;
                case 4:
                    /* 11383 */ name = "Lebap";
                    /* 11384 */ break;
                case 5:
                    /* 11386 */ name = "Mary";
            }
        }

        /* 11390 */ if (country_code.equals("TN") == true) /* 11391 */ {
            switch (region_code2) {
                case 2:
                    /* 11393 */ name = "Kasserine";
                    /* 11394 */ break;
                case 3:
                    /* 11396 */ name = "Kairouan";
                    /* 11397 */ break;
                case 6:
                    /* 11399 */ name = "Jendouba";
                    /* 11400 */ break;
                case 10:
                    /* 11402 */ name = "Qafsah";
                    /* 11403 */ break;
                case 14:
                    /* 11405 */ name = "El Kef";
                    /* 11406 */ break;
                case 15:
                    /* 11408 */ name = "Al Mahdia";
                    /* 11409 */ break;
                case 16:
                    /* 11411 */ name = "Al Munastir";
                    /* 11412 */ break;
                case 17:
                    /* 11414 */ name = "Bajah";
                    /* 11415 */ break;
                case 18:
                    /* 11417 */ name = "Bizerte";
                    /* 11418 */ break;
                case 19:
                    /* 11420 */ name = "Nabeul";
                    /* 11421 */ break;
                case 22:
                    /* 11423 */ name = "Siliana";
                    /* 11424 */ break;
                case 23:
                    /* 11426 */ name = "Sousse";
                    /* 11427 */ break;
                case 27:
                    /* 11429 */ name = "Ben Arous";
                    /* 11430 */ break;
                case 28:
                    /* 11432 */ name = "Madanin";
                    /* 11433 */ break;
                case 29:
                    /* 11435 */ name = "Gabes";
                    /* 11436 */ break;
                case 31:
                    /* 11438 */ name = "Kebili";
                    /* 11439 */ break;
                case 32:
                    /* 11441 */ name = "Sfax";
                    /* 11442 */ break;
                case 33:
                    /* 11444 */ name = "Sidi Bou Zid";
                    /* 11445 */ break;
                case 34:
                    /* 11447 */ name = "Tataouine";
                    /* 11448 */ break;
                case 35:
                    /* 11450 */ name = "Tozeur";
                    /* 11451 */ break;
                case 36:
                    /* 11453 */ name = "Tunis";
                    /* 11454 */ break;
                case 37:
                    /* 11456 */ name = "Zaghouan";
                    /* 11457 */ break;
                case 38:
                    /* 11459 */ name = "Aiana";
                    /* 11460 */ break;
                case 39:
                    /* 11462 */ name = "Manouba";
                case 4:
                case 5:
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                case 13:
                case 20:
                case 21:
                case 24:
                case 25:
                case 26:
                /* 11466 */ case 30:
            }
        }
        if (country_code.equals("TO") == true) {
            /* 11467 */ switch (region_code2) {
                case 1:
                    /* 11469 */ name = "Ha";
                    /* 11470 */ break;
                case 2:
                    /* 11472 */ name = "Tongatapu";
                    /* 11473 */ break;
                case 3:
                    /* 11475 */ name = "Vava";
            }
        }

        /* 11479 */ if (country_code.equals("TR") == true) /* 11480 */ {
            switch (region_code2) {
                case 2:
                    /* 11482 */ name = "Adiyaman";
                    /* 11483 */ break;
                case 3:
                    /* 11485 */ name = "Afyonkarahisar";
                    /* 11486 */ break;
                case 4:
                    /* 11488 */ name = "Agri";
                    /* 11489 */ break;
                case 5:
                    /* 11491 */ name = "Amasya";
                    /* 11492 */ break;
                case 7:
                    /* 11494 */ name = "Antalya";
                    /* 11495 */ break;
                case 8:
                    /* 11497 */ name = "Artvin";
                    /* 11498 */ break;
                case 9:
                    /* 11500 */ name = "Aydin";
                    /* 11501 */ break;
                case 10:
                    /* 11503 */ name = "Balikesir";
                    /* 11504 */ break;
                case 11:
                    /* 11506 */ name = "Bilecik";
                    /* 11507 */ break;
                case 12:
                    /* 11509 */ name = "Bingol";
                    /* 11510 */ break;
                case 13:
                    /* 11512 */ name = "Bitlis";
                    /* 11513 */ break;
                case 14:
                    /* 11515 */ name = "Bolu";
                    /* 11516 */ break;
                case 15:
                    /* 11518 */ name = "Burdur";
                    /* 11519 */ break;
                case 16:
                    /* 11521 */ name = "Bursa";
                    /* 11522 */ break;
                case 17:
                    /* 11524 */ name = "Canakkale";
                    /* 11525 */ break;
                case 19:
                    /* 11527 */ name = "Corum";
                    /* 11528 */ break;
                case 20:
                    /* 11530 */ name = "Denizli";
                    /* 11531 */ break;
                case 21:
                    /* 11533 */ name = "Diyarbakir";
                    /* 11534 */ break;
                case 22:
                    /* 11536 */ name = "Edirne";
                    /* 11537 */ break;
                case 23:
                    /* 11539 */ name = "Elazig";
                    /* 11540 */ break;
                case 24:
                    /* 11542 */ name = "Erzincan";
                    /* 11543 */ break;
                case 25:
                    /* 11545 */ name = "Erzurum";
                    /* 11546 */ break;
                case 26:
                    /* 11548 */ name = "Eskisehir";
                    /* 11549 */ break;
                case 28:
                    /* 11551 */ name = "Giresun";
                    /* 11552 */ break;
                case 31:
                    /* 11554 */ name = "Hatay";
                    /* 11555 */ break;
                case 32:
                    /* 11557 */ name = "Mersin";
                    /* 11558 */ break;
                case 33:
                    /* 11560 */ name = "Isparta";
                    /* 11561 */ break;
                case 34:
                    /* 11563 */ name = "Istanbul";
                    /* 11564 */ break;
                case 35:
                    /* 11566 */ name = "Izmir";
                    /* 11567 */ break;
                case 37:
                    /* 11569 */ name = "Kastamonu";
                    /* 11570 */ break;
                case 38:
                    /* 11572 */ name = "Kayseri";
                    /* 11573 */ break;
                case 39:
                    /* 11575 */ name = "Kirklareli";
                    /* 11576 */ break;
                case 40:
                    /* 11578 */ name = "Kirsehir";
                    /* 11579 */ break;
                case 41:
                    /* 11581 */ name = "Kocaeli";
                    /* 11582 */ break;
                case 43:
                    /* 11584 */ name = "Kutahya";
                    /* 11585 */ break;
                case 44:
                    /* 11587 */ name = "Malatya";
                    /* 11588 */ break;
                case 45:
                    /* 11590 */ name = "Manisa";
                    /* 11591 */ break;
                case 46:
                    /* 11593 */ name = "Kahramanmaras";
                    /* 11594 */ break;
                case 48:
                    /* 11596 */ name = "Mugla";
                    /* 11597 */ break;
                case 49:
                    /* 11599 */ name = "Mus";
                    /* 11600 */ break;
                case 50:
                    /* 11602 */ name = "Nevsehir";
                    /* 11603 */ break;
                case 52:
                    /* 11605 */ name = "Ordu";
                    /* 11606 */ break;
                case 53:
                    /* 11608 */ name = "Rize";
                    /* 11609 */ break;
                case 54:
                    /* 11611 */ name = "Sakarya";
                    /* 11612 */ break;
                case 55:
                    /* 11614 */ name = "Samsun";
                    /* 11615 */ break;
                case 57:
                    /* 11617 */ name = "Sinop";
                    /* 11618 */ break;
                case 58:
                    /* 11620 */ name = "Sivas";
                    /* 11621 */ break;
                case 59:
                    /* 11623 */ name = "Tekirdag";
                    /* 11624 */ break;
                case 60:
                    /* 11626 */ name = "Tokat";
                    /* 11627 */ break;
                case 61:
                    /* 11629 */ name = "Trabzon";
                    /* 11630 */ break;
                case 62:
                    /* 11632 */ name = "Tunceli";
                    /* 11633 */ break;
                case 63:
                    /* 11635 */ name = "Sanliurfa";
                    /* 11636 */ break;
                case 64:
                    /* 11638 */ name = "Usak";
                    /* 11639 */ break;
                case 65:
                    /* 11641 */ name = "Van";
                    /* 11642 */ break;
                case 66:
                    /* 11644 */ name = "Yozgat";
                    /* 11645 */ break;
                case 68:
                    /* 11647 */ name = "Ankara";
                    /* 11648 */ break;
                case 69:
                    /* 11650 */ name = "Gumushane";
                    /* 11651 */ break;
                case 70:
                    /* 11653 */ name = "Hakkari";
                    /* 11654 */ break;
                case 71:
                    /* 11656 */ name = "Konya";
                    /* 11657 */ break;
                case 72:
                    /* 11659 */ name = "Mardin";
                    /* 11660 */ break;
                case 73:
                    /* 11662 */ name = "Nigde";
                    /* 11663 */ break;
                case 74:
                    /* 11665 */ name = "Siirt";
                    /* 11666 */ break;
                case 75:
                    /* 11668 */ name = "Aksaray";
                    /* 11669 */ break;
                case 76:
                    /* 11671 */ name = "Batman";
                    /* 11672 */ break;
                case 77:
                    /* 11674 */ name = "Bayburt";
                    /* 11675 */ break;
                case 78:
                    /* 11677 */ name = "Karaman";
                    /* 11678 */ break;
                case 79:
                    /* 11680 */ name = "Kirikkale";
                    /* 11681 */ break;
                case 80:
                    /* 11683 */ name = "Sirnak";
                    /* 11684 */ break;
                case 81:
                    /* 11686 */ name = "Adana";
                    /* 11687 */ break;
                case 82:
                    /* 11689 */ name = "Cankiri";
                    /* 11690 */ break;
                case 83:
                    /* 11692 */ name = "Gaziantep";
                    /* 11693 */ break;
                case 84:
                    /* 11695 */ name = "Kars";
                    /* 11696 */ break;
                case 85:
                    /* 11698 */ name = "Zonguldak";
                    /* 11699 */ break;
                case 86:
                    /* 11701 */ name = "Ardahan";
                    /* 11702 */ break;
                case 87:
                    /* 11704 */ name = "Bartin";
                    /* 11705 */ break;
                case 88:
                    /* 11707 */ name = "Igdir";
                    /* 11708 */ break;
                case 89:
                    /* 11710 */ name = "Karabuk";
                    /* 11711 */ break;
                case 90:
                    /* 11713 */ name = "Kilis";
                    /* 11714 */ break;
                case 91:
                    /* 11716 */ name = "Osmaniye";
                    /* 11717 */ break;
                case 92:
                    /* 11719 */ name = "Yalova";
                    /* 11720 */ break;
                case 93:
                    /* 11722 */ name = "Duzce";
                case 6:
                case 18:
                case 27:
                case 29:
                case 30:
                case 36:
                case 42:
                case 47:
                case 51:
                case 56:
                /* 11726 */ case 67:
            }
        }
        if (country_code.equals("TT") == true) {
            /* 11727 */ switch (region_code2) {
                case 1:
                    /* 11729 */ name = "Arima";
                    /* 11730 */ break;
                case 2:
                    /* 11732 */ name = "Caroni";
                    /* 11733 */ break;
                case 3:
                    /* 11735 */ name = "Mayaro";
                    /* 11736 */ break;
                case 4:
                    /* 11738 */ name = "Nariva";
                    /* 11739 */ break;
                case 5:
                    /* 11741 */ name = "Port-of-Spain";
                    /* 11742 */ break;
                case 6:
                    /* 11744 */ name = "Saint Andrew";
                    /* 11745 */ break;
                case 7:
                    /* 11747 */ name = "Saint David";
                    /* 11748 */ break;
                case 8:
                    /* 11750 */ name = "Saint George";
                    /* 11751 */ break;
                case 9:
                    /* 11753 */ name = "Saint Patrick";
                    /* 11754 */ break;
                case 10:
                    /* 11756 */ name = "San Fernando";
                    /* 11757 */ break;
                case 11:
                    /* 11759 */ name = "Tobago";
                    /* 11760 */ break;
                case 12:
                    /* 11762 */ name = "Victoria";
            }
        }

        /* 11766 */ if (country_code.equals("TW") == true) {
            /* 11767 */ switch (region_code2) {
                case 1:
                    /* 11769 */ name = "Fu-chien";
                    /* 11770 */ break;
                case 2:
                    /* 11772 */ name = "Kao-hsiung";
                    /* 11773 */ break;
                case 3:
                    /* 11775 */ name = "T'ai-pei";
                    /* 11776 */ break;
                case 4:
                    /* 11778 */ name = "T'ai-wan";
            }
        }

        /* 11782 */ if (country_code.equals("TZ") == true) {
            /* 11783 */ switch (region_code2) {
                case 2:
                    /* 11785 */ name = "Pwani";
                    /* 11786 */ break;
                case 3:
                    /* 11788 */ name = "Dodoma";
                    /* 11789 */ break;
                case 4:
                    /* 11791 */ name = "Iringa";
                    /* 11792 */ break;
                case 5:
                    /* 11794 */ name = "Kigoma";
                    /* 11795 */ break;
                case 6:
                    /* 11797 */ name = "Kilimanjaro";
                    /* 11798 */ break;
                case 7:
                    /* 11800 */ name = "Lindi";
                    /* 11801 */ break;
                case 8:
                    /* 11803 */ name = "Mara";
                    /* 11804 */ break;
                case 9:
                    /* 11806 */ name = "Mbeya";
                    /* 11807 */ break;
                case 10:
                    /* 11809 */ name = "Morogoro";
                    /* 11810 */ break;
                case 11:
                    /* 11812 */ name = "Mtwara";
                    /* 11813 */ break;
                case 12:
                    /* 11815 */ name = "Mwanza";
                    /* 11816 */ break;
                case 13:
                    /* 11818 */ name = "Pemba North";
                    /* 11819 */ break;
                case 14:
                    /* 11821 */ name = "Ruvuma";
                    /* 11822 */ break;
                case 15:
                    /* 11824 */ name = "Shinyanga";
                    /* 11825 */ break;
                case 16:
                    /* 11827 */ name = "Singida";
                    /* 11828 */ break;
                case 17:
                    /* 11830 */ name = "Tabora";
                    /* 11831 */ break;
                case 18:
                    /* 11833 */ name = "Tanga";
                    /* 11834 */ break;
                case 19:
                    /* 11836 */ name = "Kagera";
                    /* 11837 */ break;
                case 20:
                    /* 11839 */ name = "Pemba South";
                    /* 11840 */ break;
                case 21:
                    /* 11842 */ name = "Zanzibar Central";
                    /* 11843 */ break;
                case 22:
                    /* 11845 */ name = "Zanzibar North";
                    /* 11846 */ break;
                case 23:
                    /* 11848 */ name = "Dar es Salaam";
                    /* 11849 */ break;
                case 24:
                    /* 11851 */ name = "Rukwa";
                    /* 11852 */ break;
                case 25:
                    /* 11854 */ name = "Zanzibar Urban";
                    /* 11855 */ break;
                case 26:
                    /* 11857 */ name = "Arusha";
                    /* 11858 */ break;
                case 27:
                    /* 11860 */ name = "Manyara";
            }
        }

        /* 11864 */ if (country_code.equals("UA") == true) {
            /* 11865 */ switch (region_code2) {
                case 1:
                    /* 11867 */ name = "Cherkas'ka Oblast'";
                    /* 11868 */ break;
                case 2:
                    /* 11870 */ name = "Chernihivs'ka Oblast'";
                    /* 11871 */ break;
                case 3:
                    /* 11873 */ name = "Chernivets'ka Oblast'";
                    /* 11874 */ break;
                case 4:
                    /* 11876 */ name = "Dnipropetrovs'ka Oblast'";
                    /* 11877 */ break;
                case 5:
                    /* 11879 */ name = "Donets'ka Oblast'";
                    /* 11880 */ break;
                case 6:
                    /* 11882 */ name = "Ivano-Frankivs'ka Oblast'";
                    /* 11883 */ break;
                case 7:
                    /* 11885 */ name = "Kharkivs'ka Oblast'";
                    /* 11886 */ break;
                case 8:
                    /* 11888 */ name = "Khersons'ka Oblast'";
                    /* 11889 */ break;
                case 9:
                    /* 11891 */ name = "Khmel'nyts'ka Oblast'";
                    /* 11892 */ break;
                case 10:
                    /* 11894 */ name = "Kirovohrads'ka Oblast'";
                    /* 11895 */ break;
                case 11:
                    /* 11897 */ name = "Krym";
                    /* 11898 */ break;
                case 12:
                    /* 11900 */ name = "Kyyiv";
                    /* 11901 */ break;
                case 13:
                    /* 11903 */ name = "Kyyivs'ka Oblast'";
                    /* 11904 */ break;
                case 14:
                    /* 11906 */ name = "Luhans'ka Oblast'";
                    /* 11907 */ break;
                case 15:
                    /* 11909 */ name = "L'vivs'ka Oblast'";
                    /* 11910 */ break;
                case 16:
                    /* 11912 */ name = "Mykolayivs'ka Oblast'";
                    /* 11913 */ break;
                case 17:
                    /* 11915 */ name = "Odes'ka Oblast'";
                    /* 11916 */ break;
                case 18:
                    /* 11918 */ name = "Poltavs'ka Oblast'";
                    /* 11919 */ break;
                case 19:
                    /* 11921 */ name = "Rivnens'ka Oblast'";
                    /* 11922 */ break;
                case 20:
                    /* 11924 */ name = "Sevastopol'";
                    /* 11925 */ break;
                case 21:
                    /* 11927 */ name = "Sums'ka Oblast'";
                    /* 11928 */ break;
                case 22:
                    /* 11930 */ name = "Ternopil's'ka Oblast'";
                    /* 11931 */ break;
                case 23:
                    /* 11933 */ name = "Vinnyts'ka Oblast'";
                    /* 11934 */ break;
                case 24:
                    /* 11936 */ name = "Volyns'ka Oblast'";
                    /* 11937 */ break;
                case 25:
                    /* 11939 */ name = "Zakarpats'ka Oblast'";
                    /* 11940 */ break;
                case 26:
                    /* 11942 */ name = "Zaporiz'ka Oblast'";
                    /* 11943 */ break;
                case 27:
                    /* 11945 */ name = "Zhytomyrs'ka Oblast'";
            }
        }

        /* 11949 */ if (country_code.equals("UG") == true) /* 11950 */ {
            switch (region_code2) {
                case 26:
                    /* 11952 */ name = "Apac";
                    /* 11953 */ break;
                case 28:
                    /* 11955 */ name = "Bundibugyo";
                    /* 11956 */ break;
                case 29:
                    /* 11958 */ name = "Bushenyi";
                    /* 11959 */ break;
                case 30:
                    /* 11961 */ name = "Gulu";
                    /* 11962 */ break;
                case 31:
                    /* 11964 */ name = "Hoima";
                    /* 11965 */ break;
                case 33:
                    /* 11967 */ name = "Jinja";
                    /* 11968 */ break;
                case 36:
                    /* 11970 */ name = "Kalangala";
                    /* 11971 */ break;
                case 37:
                    /* 11973 */ name = "Kampala";
                    /* 11974 */ break;
                case 38:
                    /* 11976 */ name = "Kamuli";
                    /* 11977 */ break;
                case 39:
                    /* 11979 */ name = "Kapchorwa";
                    /* 11980 */ break;
                case 40:
                    /* 11982 */ name = "Kasese";
                    /* 11983 */ break;
                case 41:
                    /* 11985 */ name = "Kibale";
                    /* 11986 */ break;
                case 42:
                    /* 11988 */ name = "Kiboga";
                    /* 11989 */ break;
                case 43:
                    /* 11991 */ name = "Kisoro";
                    /* 11992 */ break;
                case 45:
                    /* 11994 */ name = "Kotido";
                    /* 11995 */ break;
                case 46:
                    /* 11997 */ name = "Kumi";
                    /* 11998 */ break;
                case 47:
                    /* 12000 */ name = "Lira";
                    /* 12001 */ break;
                case 50:
                    /* 12003 */ name = "Masindi";
                    /* 12004 */ break;
                case 52:
                    /* 12006 */ name = "Mbarara";
                    /* 12007 */ break;
                case 56:
                    /* 12009 */ name = "Mubende";
                    /* 12010 */ break;
                case 58:
                    /* 12012 */ name = "Nebbi";
                    /* 12013 */ break;
                case 59:
                    /* 12015 */ name = "Ntungamo";
                    /* 12016 */ break;
                case 60:
                    /* 12018 */ name = "Pallisa";
                    /* 12019 */ break;
                case 61:
                    /* 12021 */ name = "Rakai";
                    /* 12022 */ break;
                case 65:
                    /* 12024 */ name = "Adjumani";
                    /* 12025 */ break;
                case 66:
                    /* 12027 */ name = "Bugiri";
                    /* 12028 */ break;
                case 67:
                    /* 12030 */ name = "Busia";
                    /* 12031 */ break;
                case 69:
                    /* 12033 */ name = "Katakwi";
                    /* 12034 */ break;
                case 70:
                    /* 12036 */ name = "Luwero";
                    /* 12037 */ break;
                case 71:
                    /* 12039 */ name = "Masaka";
                    /* 12040 */ break;
                case 72:
                    /* 12042 */ name = "Moyo";
                    /* 12043 */ break;
                case 73:
                    /* 12045 */ name = "Nakasongola";
                    /* 12046 */ break;
                case 74:
                    /* 12048 */ name = "Sembabule";
                    /* 12049 */ break;
                case 76:
                    /* 12051 */ name = "Tororo";
                    /* 12052 */ break;
                case 77:
                    /* 12054 */ name = "Arua";
                    /* 12055 */ break;
                case 78:
                    /* 12057 */ name = "Iganga";
                    /* 12058 */ break;
                case 79:
                    /* 12060 */ name = "Kabarole";
                    /* 12061 */ break;
                case 80:
                    /* 12063 */ name = "Kaberamaido";
                    /* 12064 */ break;
                case 81:
                    /* 12066 */ name = "Kamwenge";
                    /* 12067 */ break;
                case 82:
                    /* 12069 */ name = "Kanungu";
                    /* 12070 */ break;
                case 83:
                    /* 12072 */ name = "Kayunga";
                    /* 12073 */ break;
                case 84:
                    /* 12075 */ name = "Kitgum";
                    /* 12076 */ break;
                case 85:
                    /* 12078 */ name = "Kyenjojo";
                    /* 12079 */ break;
                case 86:
                    /* 12081 */ name = "Mayuge";
                    /* 12082 */ break;
                case 87:
                    /* 12084 */ name = "Mbale";
                    /* 12085 */ break;
                case 88:
                    /* 12087 */ name = "Moroto";
                    /* 12088 */ break;
                case 89:
                    /* 12090 */ name = "Mpigi";
                    /* 12091 */ break;
                case 90:
                    /* 12093 */ name = "Mukono";
                    /* 12094 */ break;
                case 91:
                    /* 12096 */ name = "Nakapiripirit";
                    /* 12097 */ break;
                case 92:
                    /* 12099 */ name = "Pader";
                    /* 12100 */ break;
                case 93:
                    /* 12102 */ name = "Rukungiri";
                    /* 12103 */ break;
                case 94:
                    /* 12105 */ name = "Sironko";
                    /* 12106 */ break;
                case 95:
                    /* 12108 */ name = "Soroti";
                    /* 12109 */ break;
                case 96:
                    /* 12111 */ name = "Wakiso";
                    /* 12112 */ break;
                case 97:
                    /* 12114 */ name = "Yumbe";
                case 27:
                case 32:
                case 34:
                case 35:
                case 44:
                case 48:
                case 49:
                case 51:
                case 53:
                case 54:
                case 55:
                case 57:
                case 62:
                case 63:
                case 64:
                case 68:
                /* 12118 */ case 75:
            }
        }
        if (country_code.equals("UY") == true) {
            /* 12119 */ switch (region_code2) {
                case 1:
                    /* 12121 */ name = "Artigas";
                    /* 12122 */ break;
                case 2:
                    /* 12124 */ name = "Canelones";
                    /* 12125 */ break;
                case 3:
                    /* 12127 */ name = "Cerro Largo";
                    /* 12128 */ break;
                case 4:
                    /* 12130 */ name = "Colonia";
                    /* 12131 */ break;
                case 5:
                    /* 12133 */ name = "Durazno";
                    /* 12134 */ break;
                case 6:
                    /* 12136 */ name = "Flores";
                    /* 12137 */ break;
                case 7:
                    /* 12139 */ name = "Florida";
                    /* 12140 */ break;
                case 8:
                    /* 12142 */ name = "Lavalleja";
                    /* 12143 */ break;
                case 9:
                    /* 12145 */ name = "Maldonado";
                    /* 12146 */ break;
                case 10:
                    /* 12148 */ name = "Montevideo";
                    /* 12149 */ break;
                case 11:
                    /* 12151 */ name = "Paysandu";
                    /* 12152 */ break;
                case 12:
                    /* 12154 */ name = "Rio Negro";
                    /* 12155 */ break;
                case 13:
                    /* 12157 */ name = "Rivera";
                    /* 12158 */ break;
                case 14:
                    /* 12160 */ name = "Rocha";
                    /* 12161 */ break;
                case 15:
                    /* 12163 */ name = "Salto";
                    /* 12164 */ break;
                case 16:
                    /* 12166 */ name = "San Jose";
                    /* 12167 */ break;
                case 17:
                    /* 12169 */ name = "Soriano";
                    /* 12170 */ break;
                case 18:
                    /* 12172 */ name = "Tacuarembo";
                    /* 12173 */ break;
                case 19:
                    /* 12175 */ name = "Treinta y Tres";
            }
        }

        /* 12179 */ if (country_code.equals("UZ") == true) {
            /* 12180 */ switch (region_code2) {
                case 1:
                    /* 12182 */ name = "Andijon";
                    /* 12183 */ break;
                case 2:
                    /* 12185 */ name = "Bukhoro";
                    /* 12186 */ break;
                case 3:
                    /* 12188 */ name = "Farghona";
                    /* 12189 */ break;
                case 4:
                    /* 12191 */ name = "Jizzakh";
                    /* 12192 */ break;
                case 5:
                    /* 12194 */ name = "Khorazm";
                    /* 12195 */ break;
                case 6:
                    /* 12197 */ name = "Namangan";
                    /* 12198 */ break;
                case 7:
                    /* 12200 */ name = "Nawoiy";
                    /* 12201 */ break;
                case 8:
                    /* 12203 */ name = "Qashqadaryo";
                    /* 12204 */ break;
                case 9:
                    /* 12206 */ name = "Qoraqalpoghiston";
                    /* 12207 */ break;
                case 10:
                    /* 12209 */ name = "Samarqand";
                    /* 12210 */ break;
                case 11:
                    /* 12212 */ name = "Sirdaryo";
                    /* 12213 */ break;
                case 12:
                    /* 12215 */ name = "Surkhondaryo";
                    /* 12216 */ break;
                case 13:
                    /* 12218 */ name = "Toshkent";
                    /* 12219 */ break;
                case 14:
                    /* 12221 */ name = "Toshkent";
            }
        }

        /* 12225 */ if (country_code.equals("VC") == true) {
            /* 12226 */ switch (region_code2) {
                case 1:
                    /* 12228 */ name = "Charlotte";
                    /* 12229 */ break;
                case 2:
                    /* 12231 */ name = "Saint Andrew";
                    /* 12232 */ break;
                case 3:
                    /* 12234 */ name = "Saint David";
                    /* 12235 */ break;
                case 4:
                    /* 12237 */ name = "Saint George";
                    /* 12238 */ break;
                case 5:
                    /* 12240 */ name = "Saint Patrick";
                    /* 12241 */ break;
                case 6:
                    /* 12243 */ name = "Grenadines";
            }
        }

        /* 12247 */ if (country_code.equals("VE") == true) {
            /* 12248 */ switch (region_code2) {
                case 1:
                    /* 12250 */ name = "Amazonas";
                    /* 12251 */ break;
                case 2:
                    /* 12253 */ name = "Anzoategui";
                    /* 12254 */ break;
                case 3:
                    /* 12256 */ name = "Apure";
                    /* 12257 */ break;
                case 4:
                    /* 12259 */ name = "Aragua";
                    /* 12260 */ break;
                case 5:
                    /* 12262 */ name = "Barinas";
                    /* 12263 */ break;
                case 6:
                    /* 12265 */ name = "Bolivar";
                    /* 12266 */ break;
                case 7:
                    /* 12268 */ name = "Carabobo";
                    /* 12269 */ break;
                case 8:
                    /* 12271 */ name = "Cojedes";
                    /* 12272 */ break;
                case 9:
                    /* 12274 */ name = "Delta Amacuro";
                    /* 12275 */ break;
                case 11:
                    /* 12277 */ name = "Falcon";
                    /* 12278 */ break;
                case 12:
                    /* 12280 */ name = "Guarico";
                    /* 12281 */ break;
                case 13:
                    /* 12283 */ name = "Lara";
                    /* 12284 */ break;
                case 14:
                    /* 12286 */ name = "Merida";
                    /* 12287 */ break;
                case 15:
                    /* 12289 */ name = "Miranda";
                    /* 12290 */ break;
                case 16:
                    /* 12292 */ name = "Monagas";
                    /* 12293 */ break;
                case 17:
                    /* 12295 */ name = "Nueva Esparta";
                    /* 12296 */ break;
                case 18:
                    /* 12298 */ name = "Portuguesa";
                    /* 12299 */ break;
                case 19:
                    /* 12301 */ name = "Sucre";
                    /* 12302 */ break;
                case 20:
                    /* 12304 */ name = "Tachira";
                    /* 12305 */ break;
                case 21:
                    /* 12307 */ name = "Trujillo";
                    /* 12308 */ break;
                case 22:
                    /* 12310 */ name = "Yaracuy";
                    /* 12311 */ break;
                case 23:
                    /* 12313 */ name = "Zulia";
                    /* 12314 */ break;
                case 24:
                    /* 12316 */ name = "Dependencias Federales";
                    /* 12317 */ break;
                case 25:
                    /* 12319 */ name = "Distrito Federal";
                    /* 12320 */ break;
                case 26:
                    /* 12322 */ name = "Vargas";
                case 10:
            }
        }
        /* 12326 */ if (country_code.equals("VN") == true) /* 12327 */ {
            switch (region_code2) {
                case 1:
                    /* 12329 */ name = "An Giang";
                    /* 12330 */ break;
                case 3:
                    /* 12332 */ name = "Ben Tre";
                    /* 12333 */ break;
                case 5:
                    /* 12335 */ name = "Cao Bang";
                    /* 12336 */ break;
                case 9:
                    /* 12338 */ name = "Dong Thap";
                    /* 12339 */ break;
                case 13:
                    /* 12341 */ name = "Hai Phong";
                    /* 12342 */ break;
                case 20:
                    /* 12344 */ name = "Ho Chi Minh";
                    /* 12345 */ break;
                case 21:
                    /* 12347 */ name = "Kien Giang";
                    /* 12348 */ break;
                case 23:
                    /* 12350 */ name = "Lam Dong";
                    /* 12351 */ break;
                case 24:
                    /* 12353 */ name = "Long An";
                    /* 12354 */ break;
                case 30:
                    /* 12356 */ name = "Quang Ninh";
                    /* 12357 */ break;
                case 32:
                    /* 12359 */ name = "Son La";
                    /* 12360 */ break;
                case 33:
                    /* 12362 */ name = "Tay Ninh";
                    /* 12363 */ break;
                case 34:
                    /* 12365 */ name = "Thanh Hoa";
                    /* 12366 */ break;
                case 35:
                    /* 12368 */ name = "Thai Binh";
                    /* 12369 */ break;
                case 37:
                    /* 12371 */ name = "Tien Giang";
                    /* 12372 */ break;
                case 39:
                    /* 12374 */ name = "Lang Son";
                    /* 12375 */ break;
                case 43:
                    /* 12377 */ name = "An Giang";
                    /* 12378 */ break;
                case 44:
                    /* 12380 */ name = "Dac Lac";
                    /* 12381 */ break;
                case 45:
                    /* 12383 */ name = "Dong Nai";
                    /* 12384 */ break;
                case 46:
                    /* 12386 */ name = "Dong Thap";
                    /* 12387 */ break;
                case 47:
                    /* 12389 */ name = "Kien Giang";
                    /* 12390 */ break;
                case 49:
                    /* 12392 */ name = "Song Be";
                    /* 12393 */ break;
                case 50:
                    /* 12395 */ name = "Vinh Phu";
                    /* 12396 */ break;
                case 51:
                    /* 12398 */ name = "Ha Noi";
                    /* 12399 */ break;
                case 52:
                    /* 12401 */ name = "Ho Chi Minh";
                    /* 12402 */ break;
                case 53:
                    /* 12404 */ name = "Ba Ria-Vung Tau";
                    /* 12405 */ break;
                case 54:
                    /* 12407 */ name = "Binh Dinh";
                    /* 12408 */ break;
                case 55:
                    /* 12410 */ name = "Binh Thuan";
                    /* 12411 */ break;
                case 58:
                    /* 12413 */ name = "Ha Giang";
                    /* 12414 */ break;
                case 59:
                    /* 12416 */ name = "Ha Tay";
                    /* 12417 */ break;
                case 60:
                    /* 12419 */ name = "Ha Tinh";
                    /* 12420 */ break;
                case 61:
                    /* 12422 */ name = "Hoa Binh";
                    /* 12423 */ break;
                case 62:
                    /* 12425 */ name = "Khanh Hoa";
                    /* 12426 */ break;
                case 63:
                    /* 12428 */ name = "Kon Tum";
                    /* 12429 */ break;
                case 64:
                    /* 12431 */ name = "Quang Tri";
                    /* 12432 */ break;
                case 65:
                    /* 12434 */ name = "Nam Ha";
                    /* 12435 */ break;
                case 66:
                    /* 12437 */ name = "Nghe An";
                    /* 12438 */ break;
                case 67:
                    /* 12440 */ name = "Ninh Binh";
                    /* 12441 */ break;
                case 68:
                    /* 12443 */ name = "Ninh Thuan";
                    /* 12444 */ break;
                case 69:
                    /* 12446 */ name = "Phu Yen";
                    /* 12447 */ break;
                case 70:
                    /* 12449 */ name = "Quang Binh";
                    /* 12450 */ break;
                case 71:
                    /* 12452 */ name = "Quang Ngai";
                    /* 12453 */ break;
                case 72:
                    /* 12455 */ name = "Quang Tri";
                    /* 12456 */ break;
                case 73:
                    /* 12458 */ name = "Soc Trang";
                    /* 12459 */ break;
                case 74:
                    /* 12461 */ name = "Thua Thien";
                    /* 12462 */ break;
                case 75:
                    /* 12464 */ name = "Tra Vinh";
                    /* 12465 */ break;
                case 76:
                    /* 12467 */ name = "Tuyen Quang";
                    /* 12468 */ break;
                case 77:
                    /* 12470 */ name = "Vinh Long";
                    /* 12471 */ break;
                case 78:
                    /* 12473 */ name = "Da Nang";
                    /* 12474 */ break;
                case 79:
                    /* 12476 */ name = "Hai Duong";
                    /* 12477 */ break;
                case 80:
                    /* 12479 */ name = "Ha Nam";
                    /* 12480 */ break;
                case 81:
                    /* 12482 */ name = "Hung Yen";
                    /* 12483 */ break;
                case 82:
                    /* 12485 */ name = "Nam Dinh";
                    /* 12486 */ break;
                case 83:
                    /* 12488 */ name = "Phu Tho";
                    /* 12489 */ break;
                case 84:
                    /* 12491 */ name = "Quang Nam";
                    /* 12492 */ break;
                case 85:
                    /* 12494 */ name = "Thai Nguyen";
                    /* 12495 */ break;
                case 86:
                    /* 12497 */ name = "Vinh Puc Province";
                    /* 12498 */ break;
                case 87:
                    /* 12500 */ name = "Can Tho";
                    /* 12501 */ break;
                case 88:
                    /* 12503 */ name = "Dak Lak";
                    /* 12504 */ break;
                case 89:
                    /* 12506 */ name = "Lai Chau";
                    /* 12507 */ break;
                case 90:
                    /* 12509 */ name = "Lao Cai";
                    /* 12510 */ break;
                case 91:
                    /* 12512 */ name = "Dak Nong";
                    /* 12513 */ break;
                case 92:
                    /* 12515 */ name = "Dien Bien";
                    /* 12516 */ break;
                case 93:
                    /* 12518 */ name = "Hau Giang";
                case 2:
                case 4:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 22:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 31:
                case 36:
                case 38:
                case 40:
                case 41:
                case 42:
                case 48:
                case 56:
                /* 12522 */ case 57:
            }
        }
        if (country_code.equals("VU") == true) {
            /* 12523 */ switch (region_code2) {
                case 5:
                    /* 12525 */ name = "Ambrym";
                    /* 12526 */ break;
                case 6:
                    /* 12528 */ name = "Aoba";
                    /* 12529 */ break;
                case 7:
                    /* 12531 */ name = "Torba";
                    /* 12532 */ break;
                case 8:
                    /* 12534 */ name = "Efate";
                    /* 12535 */ break;
                case 9:
                    /* 12537 */ name = "Epi";
                    /* 12538 */ break;
                case 10:
                    /* 12540 */ name = "Malakula";
                    /* 12541 */ break;
                case 11:
                    /* 12543 */ name = "Paama";
                    /* 12544 */ break;
                case 12:
                    /* 12546 */ name = "Pentecote";
                    /* 12547 */ break;
                case 13:
                    /* 12549 */ name = "Sanma";
                    /* 12550 */ break;
                case 14:
                    /* 12552 */ name = "Shepherd";
                    /* 12553 */ break;
                case 15:
                    /* 12555 */ name = "Tafea";
                    /* 12556 */ break;
                case 16:
                    /* 12558 */ name = "Malampa";
                    /* 12559 */ break;
                case 17:
                    /* 12561 */ name = "Penama";
                    /* 12562 */ break;
                case 18:
                    /* 12564 */ name = "Shefa";
            }
        }

        /* 12568 */ if (country_code.equals("WS") == true) {
            /* 12569 */ switch (region_code2) {
                case 2:
                    /* 12571 */ name = "Aiga-i-le-Tai";
                    /* 12572 */ break;
                case 3:
                    /* 12574 */ name = "Atua";
                    /* 12575 */ break;
                case 4:
                    /* 12577 */ name = "Fa";
                    /* 12578 */ break;
                case 5:
                    /* 12580 */ name = "Gaga";
                    /* 12581 */ break;
                case 6:
                    /* 12583 */ name = "Va";
                    /* 12584 */ break;
                case 7:
                    /* 12586 */ name = "Gagaifomauga";
                    /* 12587 */ break;
                case 8:
                    /* 12589 */ name = "Palauli";
                    /* 12590 */ break;
                case 9:
                    /* 12592 */ name = "Satupa";
                    /* 12593 */ break;
                case 10:
                    /* 12595 */ name = "Tuamasaga";
                    /* 12596 */ break;
                case 11:
                    /* 12598 */ name = "Vaisigano";
            }
        }

        /* 12602 */ if (country_code.equals("YE") == true) {
            /* 12603 */ switch (region_code2) {
                case 1:
                    /* 12605 */ name = "Abyan";
                    /* 12606 */ break;
                case 2:
                    /* 12608 */ name = "Adan";
                    /* 12609 */ break;
                case 3:
                    /* 12611 */ name = "Al Mahrah";
                    /* 12612 */ break;
                case 4:
                    /* 12614 */ name = "Hadramawt";
                    /* 12615 */ break;
                case 5:
                    /* 12617 */ name = "Shabwah";
                    /* 12618 */ break;
                case 6:
                    /* 12620 */ name = "Lahij";
                    /* 12621 */ break;
                case 7:
                    /* 12623 */ name = "Al Bayda'";
                    /* 12624 */ break;
                case 8:
                    /* 12626 */ name = "Al Hudaydah";
                    /* 12627 */ break;
                case 9:
                    /* 12629 */ name = "Al Jawf";
                    /* 12630 */ break;
                case 10:
                    /* 12632 */ name = "Al Mahwit";
                    /* 12633 */ break;
                case 11:
                    /* 12635 */ name = "Dhamar";
                    /* 12636 */ break;
                case 12:
                    /* 12638 */ name = "Hajjah";
                    /* 12639 */ break;
                case 13:
                    /* 12641 */ name = "Ibb";
                    /* 12642 */ break;
                case 14:
                    /* 12644 */ name = "Ma'rib";
                    /* 12645 */ break;
                case 15:
                    /* 12647 */ name = "Sa'dah";
                    /* 12648 */ break;
                case 16:
                    /* 12650 */ name = "San'a'";
                    /* 12651 */ break;
                case 17:
                    /* 12653 */ name = "Taizz";
                    /* 12654 */ break;
                case 18:
                    /* 12656 */ name = "Ad Dali";
                    /* 12657 */ break;
                case 19:
                    /* 12659 */ name = "Amran";
                    /* 12660 */ break;
                case 20:
                    /* 12662 */ name = "Al Bayda'";
                    /* 12663 */ break;
                case 21:
                    /* 12665 */ name = "Al Jawf";
                    /* 12666 */ break;
                case 22:
                    /* 12668 */ name = "Hajjah";
                    /* 12669 */ break;
                case 23:
                    /* 12671 */ name = "Ibb";
                    /* 12672 */ break;
                case 24:
                    /* 12674 */ name = "Lahij";
                    /* 12675 */ break;
                case 25:
                    /* 12677 */ name = "Taizz";
            }
        }

        /* 12681 */ if (country_code.equals("ZA") == true) {
            /* 12682 */ switch (region_code2) {
                case 1:
                    /* 12684 */ name = "North-Western Province";
                    /* 12685 */ break;
                case 2:
                    /* 12687 */ name = "KwaZulu-Natal";
                    /* 12688 */ break;
                case 3:
                    /* 12690 */ name = "Free State";
                    /* 12691 */ break;
                case 5:
                    /* 12693 */ name = "Eastern Cape";
                    /* 12694 */ break;
                case 6:
                    /* 12696 */ name = "Gauteng";
                    /* 12697 */ break;
                case 7:
                    /* 12699 */ name = "Mpumalanga";
                    /* 12700 */ break;
                case 8:
                    /* 12702 */ name = "Northern Cape";
                    /* 12703 */ break;
                case 9:
                    /* 12705 */ name = "Limpopo";
                    /* 12706 */ break;
                case 10:
                    /* 12708 */ name = "North-West";
                    /* 12709 */ break;
                case 11:
                    /* 12711 */ name = "Western Cape";
                case 4:
            }
        }
        /* 12715 */ if (country_code.equals("ZM") == true) {
            /* 12716 */ switch (region_code2) {
                case 1:
                    /* 12718 */ name = "Western";
                    /* 12719 */ break;
                case 2:
                    /* 12721 */ name = "Central";
                    /* 12722 */ break;
                case 3:
                    /* 12724 */ name = "Eastern";
                    /* 12725 */ break;
                case 4:
                    /* 12727 */ name = "Luapula";
                    /* 12728 */ break;
                case 5:
                    /* 12730 */ name = "Northern";
                    /* 12731 */ break;
                case 6:
                    /* 12733 */ name = "North-Western";
                    /* 12734 */ break;
                case 7:
                    /* 12736 */ name = "Southern";
                    /* 12737 */ break;
                case 8:
                    /* 12739 */ name = "Copperbelt";
                    /* 12740 */ break;
                case 9:
                    /* 12742 */ name = "Lusaka";
            }
        }

        /* 12746 */ if (country_code.equals("ZW") == true) {
            /* 12747 */ switch (region_code2) {
                case 1:
                    /* 12749 */ name = "Manicaland";
                    /* 12750 */ break;
                case 2:
                    /* 12752 */ name = "Midlands";
                    /* 12753 */ break;
                case 3:
                    /* 12755 */ name = "Mashonaland Central";
                    break;
                case 4:
                    name = "Mashonaland East";
                    break;
                case 5:
                    name = "Mashonaland West";
                    break;
                case 6:
                    name = "Matabeleland North";
                    break;
                case 7:
                    name = "Matabeleland South";
                    break;
                case 8:
                    name = "Masvingo";
                    break;
                case 9:
                    name = "Bulawayo";
                    break;
                case 10:
                    name = "Harare";
            }
        }

        return name;
    }
}
