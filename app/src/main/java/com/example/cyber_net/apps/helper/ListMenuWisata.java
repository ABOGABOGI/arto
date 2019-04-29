package com.example.cyber_net.apps.helper;

import com.example.cyber_net.apps.R;
import com.example.cyber_net.apps.model.MenuModel;

import java.util.List;

public class ListMenuWisata {
    //menu wisata
    static String[] namaMenuPPOB = {"BUDAYA", "WISATA ALAM", "KELUARGA", "PEDESAAN",
            "BELANJA", "STUDY TOUR", "WISATA MALAM", "KULINER",
            "KOTA", "MENDAKI", "PETUALANGAN", "BACK PACK",
            "TRACKING", "HOBI", "RELIGI", "AGRO BISNIS"};

    static int[] gambarMenuPPOB = {R.drawable.budaya, R.drawable.wisata_alam, R.drawable.keluarga, R.drawable.pedesaan,
            R.drawable.shopping, R.drawable.study_tour, R.drawable.wisata_malam, R.drawable.kuliner,
            R.drawable.city, R.drawable.mendaki_, R.drawable.petualangan, R.drawable.backpacker,
            R.drawable.tracking, R.drawable.hobi, R.drawable.religi, R.drawable.agrotourism};

    //menu wisata
    public static List<MenuModel> getMenuWisata(List<MenuModel> listMenu) {
        for (int i = 0; i < namaMenuPPOB.length; i++) {
            MenuModel menu = new MenuModel(namaMenuPPOB[i], gambarMenuPPOB[i]);
            listMenu.add(menu);
        }
        return listMenu;
    }

    public static String[] linkPop = {
            "https://www.nativeindonesia.com/wp-content/uploads/2017/10/pemandangan-pantai-sendiki.jpg",
            "https://cdns.klimg.com/dream.co.id/resized/750x500/news/2018/03/28/80758/hari-air-sedunia-gaul-di-danau-toba-180328e_3x2.jpg",
            "http://blog.reservasi.com/wp-content/uploads/2015/08/Tidak-Liburan-Ke-Luar-Kota-Ide-Staycation-Dan-Liburan-Di-Jakarta-Ini-Akan-Sangat-Membantu-Kalian.jpg",
            "https://blog.reservasi.com/wp-content/uploads/2017/03/danau-laguna-min.jpg"
    };

    public static String[] linkPop2 = {
            "Pantai",
            "Gunung",
            "Kota",
            "Danau"
    };
}
