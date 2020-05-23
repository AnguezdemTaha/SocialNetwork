package com.example.prj_s4.Mode;

import java.sql.SQLOutput;
import java.util.Scanner;

public class maingold {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("LVL Total(numbre unites in team that you want) :");
        int lvl = sc.nextInt();
        System.out.println("Your current lvl :");
        int lvl1 = sc.nextInt();
        int[] lvl_champ = new int[lvl];
        int[] nb_champ = new int[lvl];
        int[] nb_others = new int[lvl];

        double[] probe_lvl = new double[lvl];
        double[] nbr_champs_restant = new double[lvl];
        double[] nbr_ce_champ_restant = new double[lvl];
        double[] gold_needed = new double[lvl];
        double[] proba_1_serie =new double[lvl];

        for (int i = 0; i < lvl; i++) {
            System.out.println("LVL Champ:" + (i + 1));
            lvl_champ[i] = sc.nextInt();
            System.out.println("Nombre of this champ in MAP:" + (i + 1));
            nb_champ[i] = sc.nextInt();
            System.out.println("Nombre of others champs in MAP Same lvl_champ:" + (i + 1));
            nb_others[i] = sc.nextInt();


            //double[] probe_lvl = new double[lvl];
            switch (lvl1) {
                case 1:
                    if (lvl_champ[i] == 1) {
                        probe_lvl[i] = 1;
                    }


                case 2:
                    if (lvl_champ[i] == 1) {
                        probe_lvl[i] = 1;
                    }

                case 3:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.7;
                            break;
                        case 2:
                            probe_lvl[i] = 0.3;
                            break;

                    }
                    break;
                case 4:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.5;
                            break;
                        case 2:
                            probe_lvl[i] = 0.35;
                            break;
                        case 3:
                            probe_lvl[i] = 0.15;
                            break;

                    }
                    break;
                case 5:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.35;
                            break;
                        case 2:
                            probe_lvl[i] = 0.4;
                            break;
                        case 3:
                            probe_lvl[i] = 0.2;
                            break;
                        case 4:
                            probe_lvl[i] = 0.5;
                            break;

                    }
                    break;
                case 6:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.2;
                            break;
                        case 2:
                            probe_lvl[i] = 0.35;
                            break;
                        case 3:
                            probe_lvl[i] = 0.35;
                            break;
                        case 4:
                            probe_lvl[i] = 0.1;
                            break;
                    }
                    break;

                case 7:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.14;
                            break;
                        case 2:
                            probe_lvl[i] = 0.30;
                            break;
                        case 3:
                            probe_lvl[i] = 0.4;
                            break;
                        case 4:
                            probe_lvl[i] = 0.15;
                            break;
                        case 5:
                            probe_lvl[i] = 0.01;
                            break;
                    }
                    break;

                case 8:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.14;
                            break;
                        case 2:
                            probe_lvl[i] = 0.20;
                            break;
                        case 3:
                            probe_lvl[i] = 0.35;
                            break;
                        case 4:
                            probe_lvl[i] = 0.25;
                            break;
                        case 5:
                            probe_lvl[i] = 0.06;
                            break;
                    }
                    break;

                case 9:
                    switch (lvl_champ[i]) {
                        case 1:
                            probe_lvl[i] = 0.1;
                            break;
                        case 2:
                            probe_lvl[i] = 0.15;
                            break;
                        case 3:
                            probe_lvl[i] = 0.25;
                            break;
                        case 4:
                            probe_lvl[i] = 0.35;
                            break;
                        case 5:
                            probe_lvl[i] = 0.15;
                            break;

                    }
                    break;
            }

            //double[] nbr_champs_restant = new double[lvl];
            switch (lvl_champ[i]) {
                case 1:
                    nbr_champs_restant[i] = (12 * 39) - nb_champ[i] - nb_others[i];
                    break;
                case 2:
                    nbr_champs_restant[i] = (12 * 26) - nb_champ[i] - nb_others[i];
                    break;
                case 3:
                    nbr_champs_restant[i] = (12 * 21) - nb_champ[i] - nb_others[i];
                    break;
                case 4:
                    nbr_champs_restant[i] = (9 * 13) - nb_champ[i] - nb_others[i];
                    break;
                case 5:
                    nbr_champs_restant[i] = (7 * 10) - nb_champ[i] - nb_others[i];
                    break;
            }

            //double[] nbr_ce_champ_restant = new double[lvl];
            switch (lvl_champ[i]) {
                case 1:
                    nbr_ce_champ_restant[i] = (39) - nb_champ[i];
                    break;
                case 2:
                    nbr_ce_champ_restant[i] = (26) - nb_champ[i];
                    break;
                case 3:
                    nbr_ce_champ_restant[i] = (21) - nb_champ[i];
                    break;
                case 4:
                    nbr_ce_champ_restant[i] = (13) - nb_champ[i];
                    break;
                case 5:
                    nbr_ce_champ_restant[i] = (10) - nb_champ[i];
                    System.out.println("yes the prob is here!!!");
            }

            //double[] proba_1_serie =new double[];
            proba_1_serie[i] = (nbr_ce_champ_restant[i] / nbr_champs_restant[i]) * 5 * probe_lvl[i];

            //double[] gold_needed = new double[];
            gold_needed[i] = (2 / proba_1_serie[i]);
        }

        double total_l_serie =0;
        double total_gold_needed =0;
        //i dont knwo if proba to find boiuth egail some or not !!?
        for (int i = 0; i <lvl ; i++) {
            System.out.println("Proba_serie ="+i+":" + proba_1_serie[i] + "\n" + "gold needed :" + gold_needed[i] + "\n" );
            total_l_serie =total_l_serie +proba_1_serie[i];
            //total_gold_needed=total_gold_needed+gold_needed[i];
        }
        total_gold_needed=(2/total_l_serie);
        System.out.println("total proba :"+total_l_serie +"\n" +"total gold :"+total_gold_needed);



    }
}
