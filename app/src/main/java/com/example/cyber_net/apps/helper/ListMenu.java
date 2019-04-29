package com.example.cyber_net.apps.helper;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.MenuModel;

import java.util.List;

public class ListMenu {

    //menu empat
    static String[] namaMenu1 = {"PAY", "TOP UP", "MY QR", "HISTORY"};

    static int[] gambarMenu1 = {R.drawable.pay, R.drawable.ic_add_black_24dp, R.drawable.qr,
            R.drawable.history};

    //menu laku pandai
    static String[] namaMenuLakuPandai = {"BUKA NASABAH", "SETOR TUNAI", "TARIK TUNAI"};

    static int[] gambarMenuLakuPandai = {R.drawable.nasabah, R.drawable.setor,
            R.drawable.tarik};

    //menu banyak
    static String[] namaMenu = {"PESAWAT", "KERETA API", "PELNI", "HOTEL", "PULSA", "PPOB", "WISATA", "BIOSKOP", "TABUNGAN",
            "PEMBIAYAAN", "DEPOSITO"};

    static int[] gambarMenu = {R.drawable.airplane, R.drawable.train, R.drawable.ship, R.drawable.hotel, R.drawable.gps,
            R.drawable.wallet, R.drawable.bag, R.drawable.clapperboard, R.drawable.safebox, R.drawable.interview,
            R.drawable.laku_pandai};


    //menu PPOB
    static String[] namaMenuPPOB = {"PASCA BAYAR", "LISTRIK", "PDAM", "JASTEL", "GAS PGN", "TV", "BPJS", "E-MONEY","MULTI FINANCE","PBB"};

    static int[] gambarMenuPPOB = {R.drawable.pasca, R.drawable.pln, R.drawable.pdam,
            R.drawable.telkomsek, R.drawable.gas_lpg, R.drawable.tv,
            R.drawable.asuransi, R.drawable.e_money, R.drawable.pasca,R.drawable.interview};


    //menu awal
    public static List<MenuModel> getMenu1(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenu1.length; i++) {
            MenuModel menu = new MenuModel(namaMenu1[i], gambarMenu1[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }

    //menu kedua
    public static List<MenuModel> getMenu(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenu.length; i++) {
            MenuModel menu = new MenuModel(namaMenu[i], gambarMenu[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }

    //menu laku pandai
    public static List<MenuModel> getLakuPandai(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenuLakuPandai.length; i++) {
            MenuModel menu = new MenuModel(namaMenuLakuPandai[i], gambarMenuLakuPandai[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }

    //menu ppob
    public static List<MenuModel> getPPOB(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenuPPOB.length; i++) {
            MenuModel menu = new MenuModel(namaMenuPPOB[i], gambarMenuPPOB[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }

    public static String[] link = {
            "http://radarsemarang.com/wp-content/uploads/2017/06/ANUGERAH-RADAR-KEDU-ADIT-BPR-Arto-Moro_rsz.jpg",
            "https://i0.wp.com/infokuisberhadiah.com/wp-content/uploads/2017/09/infokuisberhadiah_shopee_2017.png"
    };

    public static String[] link2 = {
            "BPR Arto Moro Semakin Dipercaya",
            "BPR Arto Moro Luncurkan Tabungan Berhadiah Mobil & motor"
    };
}