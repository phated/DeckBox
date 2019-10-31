package com.r0adkll.deckbuilder.util

import android.annotation.SuppressLint
import android.util.ArrayMap
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.Effect
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.PokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.StackedPokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.collection.model.CollectionCount
import io.pokemontcg.model.Type


object CardUtils {

    val CARDS: Array<String> = arrayOf(
            "https://images.pokemontcg.io/sm10/1.png",
            "https://images.pokemontcg.io/sm115/54.png",
            "https://images.pokemontcg.io/sm10/3.png",
            "https://images.pokemontcg.io/sm11/32.png",
            "https://images.pokemontcg.io/sm10/65.png",
            "https://images.pokemontcg.io/sm10/88.png",
            "https://images.pokemontcg.io/sm11/26.png",
            "https://images.pokemontcg.io/sm10/90.png",
            "https://images.pokemontcg.io/sm10/187.png",
            "https://images.pokemontcg.io/sm115/10.png",
            "https://images.pokemontcg.io/sm11/52.png",
            "https://images.pokemontcg.io/sm11/76.png",
            "https://images.pokemontcg.io/sm11/104.png",
            "https://images.pokemontcg.io/sm10/173.png",
            "https://images.pokemontcg.io/sm115/34.png",
            "https://images.pokemontcg.io/sm11/175.png",
            "https://images.pokemontcg.io/sm10/35.png",
            "https://images.pokemontcg.io/sm10/18.png",
            "https://images.pokemontcg.io/sm11/29.png",
            "https://images.pokemontcg.io/sm11/214.png",
            "https://images.pokemontcg.io/sm11/209.png",
            "https://images.pokemontcg.io/sm10/167.png",
            "https://images.pokemontcg.io/sm115/62.png",
            "https://images.pokemontcg.io/sm10/135.png",
            "https://images.pokemontcg.io/sm11/74.png",
            "https://images.pokemontcg.io/sm10/34.png",
            "https://images.pokemontcg.io/sm10/125.png",
            "https://images.pokemontcg.io/sm11/176.png",
            "https://images.pokemontcg.io/sm11/167.png",
            "https://images.pokemontcg.io/sm10/6.png",
            "https://images.pokemontcg.io/sm11/147.png",
            "https://images.pokemontcg.io/sm11/81.png",
            "https://images.pokemontcg.io/sm11/9.png",
            "https://images.pokemontcg.io/sm10/219.png",
            "https://images.pokemontcg.io/sm10/198.png",
            "https://images.pokemontcg.io/sm10/71.png",
            "https://images.pokemontcg.io/sm10/113.png",
            "https://images.pokemontcg.io/sm115/22.png",
            "https://images.pokemontcg.io/sm10/194.png",
            "https://images.pokemontcg.io/sm10/175.png",
            "https://images.pokemontcg.io/sm115/29.png",
            "https://images.pokemontcg.io/sm10/178.png",
            "https://images.pokemontcg.io/sm10/134.png",
            "https://images.pokemontcg.io/sm115/52.png",
            "https://images.pokemontcg.io/sm11/243.png",
            "https://images.pokemontcg.io/sm11/134.png",
            "https://images.pokemontcg.io/sm11/229.png",
            "https://images.pokemontcg.io/sm11/39.png",
            "https://images.pokemontcg.io/sm11/21.png",
            "https://images.pokemontcg.io/sm10/150.png",
            "https://images.pokemontcg.io/sm11/239.png",
            "https://images.pokemontcg.io/sm11/188.png",
            "https://images.pokemontcg.io/sm11/186.png",
            "https://images.pokemontcg.io/sm11/19.png",
            "https://images.pokemontcg.io/sm10/106.png",
            "https://images.pokemontcg.io/sm11/181.png",
            "https://images.pokemontcg.io/sm115/3.png",
            "https://images.pokemontcg.io/sm11/47.png",
            "https://images.pokemontcg.io/sm11/94.png",
            "https://images.pokemontcg.io/sm11/115.png",
            "https://images.pokemontcg.io/sm10/38.png",
            "https://images.pokemontcg.io/sm115/21.png",
            "https://images.pokemontcg.io/sm115/23.png",
            "https://images.pokemontcg.io/sm115/68.png",
            "https://images.pokemontcg.io/sm10/181.png",
            "https://images.pokemontcg.io/sm10/119.png",
            "https://images.pokemontcg.io/sm10/9.png",
            "https://images.pokemontcg.io/sm11/171.png",
            "https://images.pokemontcg.io/sm10/143.png",
            "https://images.pokemontcg.io/sm11/79.png",
            "https://images.pokemontcg.io/sm11/137.png",
            "https://images.pokemontcg.io/sm11/249.png",
            "https://images.pokemontcg.io/sm11/90.png",
            "https://images.pokemontcg.io/sm10/131.png",
            "https://images.pokemontcg.io/sm10/137.png",
            "https://images.pokemontcg.io/sm10/149.png",
            "https://images.pokemontcg.io/sm11/59.png",
            "https://images.pokemontcg.io/sm10/186.png",
            "https://images.pokemontcg.io/sm115/50.png",
            "https://images.pokemontcg.io/sm11/169.png",
            "https://images.pokemontcg.io/sm11/220.png",
            "https://images.pokemontcg.io/sm115/65.png",
            "https://images.pokemontcg.io/sm11/68.png",
            "https://images.pokemontcg.io/sm11/250.png",
            "https://images.pokemontcg.io/sm115/11.png",
            "https://images.pokemontcg.io/sm11/111.png",
            "https://images.pokemontcg.io/sm10/130.png",
            "https://images.pokemontcg.io/sm10/4.png",
            "https://images.pokemontcg.io/sm11/225.png",
            "https://images.pokemontcg.io/sm115/18.png",
            "https://images.pokemontcg.io/sm10/191.png",
            "https://images.pokemontcg.io/sm11/174.png",
            "https://images.pokemontcg.io/sm11/194.png",
            "https://images.pokemontcg.io/sm10/151.png",
            "https://images.pokemontcg.io/sm11/55.png",
            "https://images.pokemontcg.io/sm115/16.png",
            "https://images.pokemontcg.io/sm11/84.png",
            "https://images.pokemontcg.io/sm10/192.png",
            "https://images.pokemontcg.io/sm11/156.png",
            "https://images.pokemontcg.io/sm11/187.png",
            "https://images.pokemontcg.io/sm115/32.png",
            "https://images.pokemontcg.io/sm10/44.png",
            "https://images.pokemontcg.io/sm10/157.png",
            "https://images.pokemontcg.io/sm11/141.png",
            "https://images.pokemontcg.io/sm115/36.png",
            "https://images.pokemontcg.io/sm11/217.png",
            "https://images.pokemontcg.io/sm11/54.png",
            "https://images.pokemontcg.io/sm10/121.png",
            "https://images.pokemontcg.io/sm10/92.png",
            "https://images.pokemontcg.io/sm115/1.png",
            "https://images.pokemontcg.io/sm11/58.png",
            "https://images.pokemontcg.io/sm11/36.png",
            "https://images.pokemontcg.io/sm10/208.png",
            "https://images.pokemontcg.io/sm10/144.png",
            "https://images.pokemontcg.io/sm11/48.png",
            "https://images.pokemontcg.io/sm10/195.png",
            "https://images.pokemontcg.io/sm11/179.png",
            "https://images.pokemontcg.io/sm11/140.png",
            "https://images.pokemontcg.io/sm11/62.png",
            "https://images.pokemontcg.io/sm10/85.png",
            "https://images.pokemontcg.io/sm10/61.png",
            "https://images.pokemontcg.io/sm10/84.png",
            "https://images.pokemontcg.io/sm10/162.png",
            "https://images.pokemontcg.io/sm11/112.png",
            "https://images.pokemontcg.io/sm11/3.png",
            "https://images.pokemontcg.io/sm11/204.png",
            "https://images.pokemontcg.io/sm10/20.png",
            "https://images.pokemontcg.io/sm11/190.png",
            "https://images.pokemontcg.io/sm10/116.png",
            "https://images.pokemontcg.io/sm10/16.png",
            "https://images.pokemontcg.io/sm115/40.png",
            "https://images.pokemontcg.io/sm11/196.png",
            "https://images.pokemontcg.io/sm10/224.png",
            "https://images.pokemontcg.io/sm11/199.png",
            "https://images.pokemontcg.io/sm10/29.png",
            "https://images.pokemontcg.io/sm10/104.png",
            "https://images.pokemontcg.io/sm11/99.png",
            "https://images.pokemontcg.io/sm11/191.png",
            "https://images.pokemontcg.io/sm10/168.png",
            "https://images.pokemontcg.io/sm10/51.png",
            "https://images.pokemontcg.io/sm10/127.png",
            "https://images.pokemontcg.io/sm10/59.png",
            "https://images.pokemontcg.io/sm115/45.png",
            "https://images.pokemontcg.io/sm11/160.png",
            "https://images.pokemontcg.io/sm10/41.png",
            "https://images.pokemontcg.io/sm11/70.png",
            "https://images.pokemontcg.io/sm11/226.png",
            "https://images.pokemontcg.io/sm11/91.png",
            "https://images.pokemontcg.io/sm10/118.png",
            "https://images.pokemontcg.io/sm10/111.png",
            "https://images.pokemontcg.io/sm11/88.png",
            "https://images.pokemontcg.io/sm10/176.png",
            "https://images.pokemontcg.io/sm11/253.png",
            "https://images.pokemontcg.io/sm11/126.png",
            "https://images.pokemontcg.io/sm10/22.png",
            "https://images.pokemontcg.io/sm11/151.png",
            "https://images.pokemontcg.io/sm10/142.png",
            "https://images.pokemontcg.io/sm10/56.png",
            "https://images.pokemontcg.io/sm10/25.png",
            "https://images.pokemontcg.io/sm10/107.png",
            "https://images.pokemontcg.io/sm115/53.png",
            "https://images.pokemontcg.io/sm115/67.png",
            "https://images.pokemontcg.io/sm11/64.png",
            "https://images.pokemontcg.io/sm10/86.png",
            "https://images.pokemontcg.io/sm10/225.png",
            "https://images.pokemontcg.io/sm11/38.png",
            "https://images.pokemontcg.io/sm11/223.png",
            "https://images.pokemontcg.io/sm115/33.png",
            "https://images.pokemontcg.io/sm115/15.png",
            "https://images.pokemontcg.io/sm10/226.png",
            "https://images.pokemontcg.io/sm10/222.png",
            "https://images.pokemontcg.io/sm10/17.png",
            "https://images.pokemontcg.io/sm11/82.png",
            "https://images.pokemontcg.io/sm10/140.png",
            "https://images.pokemontcg.io/sm11/49.png",
            "https://images.pokemontcg.io/sm10/159.png",
            "https://images.pokemontcg.io/sm11/113.png",
            "https://images.pokemontcg.io/sm11/8.png",
            "https://images.pokemontcg.io/sm11/20.png",
            "https://images.pokemontcg.io/sm11/235.png",
            "https://images.pokemontcg.io/sm10/145.png",
            "https://images.pokemontcg.io/sm115/14.png",
            "https://images.pokemontcg.io/sm11/192.png",
            "https://images.pokemontcg.io/sm11/227.png",
            "https://images.pokemontcg.io/sm10/128.png",
            "https://images.pokemontcg.io/sm11/256.png",
            "https://images.pokemontcg.io/sm10/58.png",
            "https://images.pokemontcg.io/sm11/43.png",
            "https://images.pokemontcg.io/sm11/118.png",
            "https://images.pokemontcg.io/sm10/75.png",
            "https://images.pokemontcg.io/sm115/47.png",
            "https://images.pokemontcg.io/sm11/116.png",
            "https://images.pokemontcg.io/sm11/258.png",
            "https://images.pokemontcg.io/sm115/64.png",
            "https://images.pokemontcg.io/sm10/216.png",
            "https://images.pokemontcg.io/sm11/12.png",
            "https://images.pokemontcg.io/sm11/89.png",
            "https://images.pokemontcg.io/sm10/69.png",
            "https://images.pokemontcg.io/sm11/93.png",
            "https://images.pokemontcg.io/sm11/11.png",
            "https://images.pokemontcg.io/sm115/38.png",
            "https://images.pokemontcg.io/sm10/156.png",
            "https://images.pokemontcg.io/sm11/129.png",
            "https://images.pokemontcg.io/sm11/27.png",
            "https://images.pokemontcg.io/sm11/40.png",
            "https://images.pokemontcg.io/sm11/67.png",
            "https://images.pokemontcg.io/sm11/42.png",
            "https://images.pokemontcg.io/sm10/218.png",
            "https://images.pokemontcg.io/sm10/132.png",
            "https://images.pokemontcg.io/sm11/121.png",
            "https://images.pokemontcg.io/sm11/122.png",
            "https://images.pokemontcg.io/sm11/148.png",
            "https://images.pokemontcg.io/sm115/27.png",
            "https://images.pokemontcg.io/sm11/149.png",
            "https://images.pokemontcg.io/sm10/23.png",
            "https://images.pokemontcg.io/sm11/110.png",
            "https://images.pokemontcg.io/sm11/207.png",
            "https://images.pokemontcg.io/sm11/132.png",
            "https://images.pokemontcg.io/sm115/4.png",
            "https://images.pokemontcg.io/sm10/185.png",
            "https://images.pokemontcg.io/sm11/180.png",
            "https://images.pokemontcg.io/sm115/13.png",
            "https://images.pokemontcg.io/sm10/152.png",
            "https://images.pokemontcg.io/sm10/220.png",
            "https://images.pokemontcg.io/sm11/211.png",
            "https://images.pokemontcg.io/sm10/24.png",
            "https://images.pokemontcg.io/sm11/108.png",
            "https://images.pokemontcg.io/sm115/39.png",
            "https://images.pokemontcg.io/sm11/46.png",
            "https://images.pokemontcg.io/sm11/28.png",
            "https://images.pokemontcg.io/sm11/117.png",
            "https://images.pokemontcg.io/sm10/80.png",
            "https://images.pokemontcg.io/sm10/213.png",
            "https://images.pokemontcg.io/sm10/217.png",
            "https://images.pokemontcg.io/sm11/221.png",
            "https://images.pokemontcg.io/sm115/51.png",
            "https://images.pokemontcg.io/sm10/13.png",
            "https://images.pokemontcg.io/sm11/197.png",
            "https://images.pokemontcg.io/sm115/24.png",
            "https://images.pokemontcg.io/sm11/254.png",
            "https://images.pokemontcg.io/sm11/13.png",
            "https://images.pokemontcg.io/sm11/255.png",
            "https://images.pokemontcg.io/sm10/122.png",
            "https://images.pokemontcg.io/sm10/171.png",
            "https://images.pokemontcg.io/sm115/63.png",
            "https://images.pokemontcg.io/sm10/14.png",
            "https://images.pokemontcg.io/sm11/154.png",
            "https://images.pokemontcg.io/sm10/160.png",
            "https://images.pokemontcg.io/sm115/46.png",
            "https://images.pokemontcg.io/sm10/211.png",
            "https://images.pokemontcg.io/sm10/97.png",
            "https://images.pokemontcg.io/sm10/70.png",
            "https://images.pokemontcg.io/sm10/141.png",
            "https://images.pokemontcg.io/sm11/182.png",
            "https://images.pokemontcg.io/sm115/56.png",
            "https://images.pokemontcg.io/sm11/153.png",
            "https://images.pokemontcg.io/sm115/17.png",
            "https://images.pokemontcg.io/sm10/164.png",
            "https://images.pokemontcg.io/sm11/98.png",
            "https://images.pokemontcg.io/sm115/19.png",
            "https://images.pokemontcg.io/sm10/72.png",
            "https://images.pokemontcg.io/sm10/166.png",
            "https://images.pokemontcg.io/sm11/31.png",
            "https://images.pokemontcg.io/sm115/35.png",
            "https://images.pokemontcg.io/sm11/14.png",
            "https://images.pokemontcg.io/sm115/59.png",
            "https://images.pokemontcg.io/sm11/10.png",
            "https://images.pokemontcg.io/sm11/71.png",
            "https://images.pokemontcg.io/sm115/55.png",
            "https://images.pokemontcg.io/sm11/72.png",
            "https://images.pokemontcg.io/sm11/138.png",
            "https://images.pokemontcg.io/sm10/26.png",
            "https://images.pokemontcg.io/sm10/179.png",
            "https://images.pokemontcg.io/sm115/7.png",
            "https://images.pokemontcg.io/sm115/49.png",
            "https://images.pokemontcg.io/sm10/174.png",
            "https://images.pokemontcg.io/sm11/133.png",
            "https://images.pokemontcg.io/sm10/79.png",
            "https://images.pokemontcg.io/sm10/60.png",
            "https://images.pokemontcg.io/sm11/185.png",
            "https://images.pokemontcg.io/sm10/153.png",
            "https://images.pokemontcg.io/sm10/33.png",
            "https://images.pokemontcg.io/sm11/37.png",
            "https://images.pokemontcg.io/sm11/178.png",
            "https://images.pokemontcg.io/sm10/123.png",
            "https://images.pokemontcg.io/sm10/31.png",
            "https://images.pokemontcg.io/sm11/45.png",
            "https://images.pokemontcg.io/sm11/106.png",
            "https://images.pokemontcg.io/sm10/204.png",
            "https://images.pokemontcg.io/sm115/58.png",
            "https://images.pokemontcg.io/sm11/18.png",
            "https://images.pokemontcg.io/sm10/136.png",
            "https://images.pokemontcg.io/sm10/55.png",
            "https://images.pokemontcg.io/sm115/5.png",
            "https://images.pokemontcg.io/sm10/203.png",
            "https://images.pokemontcg.io/sm10/78.png",
            "https://images.pokemontcg.io/sm10/199.png",
            "https://images.pokemontcg.io/sm10/36.png",
            "https://images.pokemontcg.io/sm11/158.png",
            "https://images.pokemontcg.io/sm11/144.png",
            "https://images.pokemontcg.io/sm10/87.png",
            "https://images.pokemontcg.io/sm11/92.png",
            "https://images.pokemontcg.io/sm11/246.png",
            "https://images.pokemontcg.io/sm115/12.png",
            "https://images.pokemontcg.io/sm11/257.png",
            "https://images.pokemontcg.io/sm11/107.png",
            "https://images.pokemontcg.io/sm10/229.png",
            "https://images.pokemontcg.io/sm115/9.png",
            "https://images.pokemontcg.io/sm10/165.png",
            "https://images.pokemontcg.io/sm11/183.png",
            "https://images.pokemontcg.io/sm11/100.png",
            "https://images.pokemontcg.io/sm10/40.png",
            "https://images.pokemontcg.io/sm10/66.png",
            "https://images.pokemontcg.io/sm11/128.png",
            "https://images.pokemontcg.io/sm10/93.png",
            "https://images.pokemontcg.io/sm10/99.png",
            "https://images.pokemontcg.io/sm10/27.png",
            "https://images.pokemontcg.io/sm10/117.png",
            "https://images.pokemontcg.io/sm11/212.png",
            "https://images.pokemontcg.io/sm11/80.png",
            "https://images.pokemontcg.io/sm11/177.png",
            "https://images.pokemontcg.io/sm10/230.png",
            "https://images.pokemontcg.io/sm10/48.png",
            "https://images.pokemontcg.io/sm10/112.png",
            "https://images.pokemontcg.io/sm10/5.png",
            "https://images.pokemontcg.io/sm10/10.png",
            "https://images.pokemontcg.io/sm10/124.png",
            "https://images.pokemontcg.io/sm10/184.png",
            "https://images.pokemontcg.io/sm10/11.png",
            "https://images.pokemontcg.io/sm11/162.png",
            "https://images.pokemontcg.io/sm10/57.png",
            "https://images.pokemontcg.io/sm10/95.png",
            "https://images.pokemontcg.io/sm10/210.png",
            "https://images.pokemontcg.io/sm11/101.png",
            "https://images.pokemontcg.io/sm10/227.png",
            "https://images.pokemontcg.io/sm10/47.png",
            "https://images.pokemontcg.io/sm11/7.png",
            "https://images.pokemontcg.io/sm10/21.png",
            "https://images.pokemontcg.io/sm11/236.png",
            "https://images.pokemontcg.io/sm10/200.png",
            "https://images.pokemontcg.io/sm10/177.png",
            "https://images.pokemontcg.io/sm10/76.png",
            "https://images.pokemontcg.io/sm10/139.png",
            "https://images.pokemontcg.io/sm10/170.png",
            "https://images.pokemontcg.io/sm11/125.png",
            "https://images.pokemontcg.io/sm11/124.png",
            "https://images.pokemontcg.io/sm10/169.png",
            "https://images.pokemontcg.io/sm11/1.png",
            "https://images.pokemontcg.io/sm11/44.png",
            "https://images.pokemontcg.io/sm11/215.png",
            "https://images.pokemontcg.io/sm10/138.png",
            "https://images.pokemontcg.io/sm10/197.png",
            "https://images.pokemontcg.io/sm10/201.png",
            "https://images.pokemontcg.io/sm10/228.png",
            "https://images.pokemontcg.io/sm11/102.png",
            "https://images.pokemontcg.io/sm11/233.png",
            "https://images.pokemontcg.io/sm11/172.png",
            "https://images.pokemontcg.io/sm11/224.png",
            "https://images.pokemontcg.io/sm11/22.png",
            "https://images.pokemontcg.io/sm11/63.png",
            "https://images.pokemontcg.io/sm11/222.png",
            "https://images.pokemontcg.io/sm11/123.png",
            "https://images.pokemontcg.io/sm11/216.png",
            "https://images.pokemontcg.io/sm115/43.png",
            "https://images.pokemontcg.io/sm10/163.png",
            "https://images.pokemontcg.io/sm10/37.png",
            "https://images.pokemontcg.io/sm10/100.png",
            "https://images.pokemontcg.io/sm115/26.png",
            "https://images.pokemontcg.io/sm11/73.png",
            "https://images.pokemontcg.io/sm11/237.png",
            "https://images.pokemontcg.io/sm115/42.png",
            "https://images.pokemontcg.io/sm11/139.png",
            "https://images.pokemontcg.io/sm10/94.png",
            "https://images.pokemontcg.io/sm11/69.png",
            "https://images.pokemontcg.io/sm10/154.png",
            "https://images.pokemontcg.io/sm115/37.png",
            "https://images.pokemontcg.io/sm115/25.png",
            "https://images.pokemontcg.io/sm115/48.png",
            "https://images.pokemontcg.io/sm10/126.png",
            "https://images.pokemontcg.io/sm10/68.png",
            "https://images.pokemontcg.io/sm10/105.png",
            "https://images.pokemontcg.io/sm10/89.png",
            "https://images.pokemontcg.io/sm11/97.png",
            "https://images.pokemontcg.io/sm10/180.png",
            "https://images.pokemontcg.io/sm10/64.png",
            "https://images.pokemontcg.io/sm11/130.png",
            "https://images.pokemontcg.io/sm10/82.png",
            "https://images.pokemontcg.io/sm10/19.png",
            "https://images.pokemontcg.io/sm10/53.png",
            "https://images.pokemontcg.io/sm11/136.png",
            "https://images.pokemontcg.io/sm115/60.png",
            "https://images.pokemontcg.io/sm10/193.png",
            "https://images.pokemontcg.io/sm10/147.png",
            "https://images.pokemontcg.io/sm10/102.png",
            "https://images.pokemontcg.io/sm10/146.png",
            "https://images.pokemontcg.io/sm11/114.png",
            "https://images.pokemontcg.io/sm11/201.png",
            "https://images.pokemontcg.io/sm11/161.png",
            "https://images.pokemontcg.io/sm11/103.png",
            "https://images.pokemontcg.io/sm11/119.png",
            "https://images.pokemontcg.io/sm11/25.png",
            "https://images.pokemontcg.io/sm11/127.png",
            "https://images.pokemontcg.io/sm115/66.png",
            "https://images.pokemontcg.io/sm10/91.png",
            "https://images.pokemontcg.io/sm10/103.png",
            "https://images.pokemontcg.io/sm11/242.png",
            "https://images.pokemontcg.io/sm11/251.png",
            "https://images.pokemontcg.io/sm10/209.png",
            "https://images.pokemontcg.io/sm11/75.png",
            "https://images.pokemontcg.io/sm10/114.png",
            "https://images.pokemontcg.io/sm10/77.png",
            "https://images.pokemontcg.io/sm115/6.png",
            "https://images.pokemontcg.io/sm11/193.png",
            "https://images.pokemontcg.io/sm11/33.png",
            "https://images.pokemontcg.io/sm10/74.png",
            "https://images.pokemontcg.io/sm10/101.png",
            "https://images.pokemontcg.io/sm11/96.png",
            "https://images.pokemontcg.io/sm10/8.png",
            "https://images.pokemontcg.io/sm11/131.png",
            "https://images.pokemontcg.io/sm10/98.png",
            "https://images.pokemontcg.io/sm11/142.png",
            "https://images.pokemontcg.io/sm10/155.png",
            "https://images.pokemontcg.io/sm115/57.png",
            "https://images.pokemontcg.io/sm11/232.png",
            "https://images.pokemontcg.io/sm11/57.png",
            "https://images.pokemontcg.io/sm11/164.png",
            "https://images.pokemontcg.io/sm10/109.png",
            "https://images.pokemontcg.io/sm11/159.png",
            "https://images.pokemontcg.io/sm10/63.png",
            "https://images.pokemontcg.io/sm10/206.png",
            "https://images.pokemontcg.io/sm10/49.png",
            "https://images.pokemontcg.io/sm10/15.png",
            "https://images.pokemontcg.io/sm11/109.png",
            "https://images.pokemontcg.io/sm11/244.png",
            "https://images.pokemontcg.io/sm11/184.png",
            "https://images.pokemontcg.io/sm10/223.png",
            "https://images.pokemontcg.io/sm11/77.png",
            "https://images.pokemontcg.io/sm11/53.png",
            "https://images.pokemontcg.io/sm11/202.png",
            "https://images.pokemontcg.io/sm11/247.png",
            "https://images.pokemontcg.io/sm11/198.png",
            "https://images.pokemontcg.io/sm11/205.png",
            "https://images.pokemontcg.io/sm11/245.png",
            "https://images.pokemontcg.io/sm10/205.png",
            "https://images.pokemontcg.io/sm11/248.png",
            "https://images.pokemontcg.io/sm10/52.png",
            "https://images.pokemontcg.io/sm10/32.png",
            "https://images.pokemontcg.io/sm11/50.png",
            "https://images.pokemontcg.io/sm10/129.png",
            "https://images.pokemontcg.io/sm115/28.png",
            "https://images.pokemontcg.io/sm11/85.png",
            "https://images.pokemontcg.io/sm10/45.png",
            "https://images.pokemontcg.io/sm11/155.png",
            "https://images.pokemontcg.io/sm11/60.png",
            "https://images.pokemontcg.io/sm11/83.png",
            "https://images.pokemontcg.io/sm11/145.png",
            "https://images.pokemontcg.io/sm11/208.png",
            "https://images.pokemontcg.io/sm11/146.png",
            "https://images.pokemontcg.io/sm11/213.png",
            "https://images.pokemontcg.io/sm11/41.png",
            "https://images.pokemontcg.io/sm10/161.png",
            "https://images.pokemontcg.io/sm10/39.png",
            "https://images.pokemontcg.io/sm11/231.png",
            "https://images.pokemontcg.io/sm11/152.png",
            "https://images.pokemontcg.io/sm11/5.png",
            "https://images.pokemontcg.io/sm10/190.png",
            "https://images.pokemontcg.io/sm10/42.png",
            "https://images.pokemontcg.io/sm11/241.png",
            "https://images.pokemontcg.io/sm10/2.png",
            "https://images.pokemontcg.io/sm115/2.png",
            "https://images.pokemontcg.io/sm11/218.png",
            "https://images.pokemontcg.io/sm11/51.png",
            "https://images.pokemontcg.io/sm10/207.png",
            "https://images.pokemontcg.io/sm11/86.png",
            "https://images.pokemontcg.io/sm11/6.png",
            "https://images.pokemontcg.io/sm11/16.png",
            "https://images.pokemontcg.io/sm11/150.png",
            "https://images.pokemontcg.io/sm11/228.png",
            "https://images.pokemontcg.io/sm10/214.png",
            "https://images.pokemontcg.io/sm10/172.png",
            "https://images.pokemontcg.io/sm11/168.png",
            "https://images.pokemontcg.io/sm11/230.png",
            "https://images.pokemontcg.io/sm11/15.png",
            "https://images.pokemontcg.io/sm11/66.png",
            "https://images.pokemontcg.io/sm11/87.png",
            "https://images.pokemontcg.io/sm10/234.png",
            "https://images.pokemontcg.io/sm10/188.png",
            "https://images.pokemontcg.io/sm11/24.png",
            "https://images.pokemontcg.io/sm10/182.png",
            "https://images.pokemontcg.io/sm11/4.png",
            "https://images.pokemontcg.io/sm11/173.png",
            "https://images.pokemontcg.io/sm10/43.png",
            "https://images.pokemontcg.io/sm10/133.png",
            "https://images.pokemontcg.io/sm10/120.png",
            "https://images.pokemontcg.io/sm11/238.png",
            "https://images.pokemontcg.io/sm115/31.png",
            "https://images.pokemontcg.io/sm115/8.png",
            "https://images.pokemontcg.io/sm115/69.png",
            "https://images.pokemontcg.io/sm10/46.png",
            "https://images.pokemontcg.io/sm10/221.png",
            "https://images.pokemontcg.io/sm10/81.png",
            "https://images.pokemontcg.io/sm11/234.png",
            "https://images.pokemontcg.io/sm11/165.png",
            "https://images.pokemontcg.io/sm10/30.png",
            "https://images.pokemontcg.io/sm11/120.png",
            "https://images.pokemontcg.io/sm11/35.png",
            "https://images.pokemontcg.io/sm10/215.png",
            "https://images.pokemontcg.io/sm10/189.png",
            "https://images.pokemontcg.io/sm11/170.png",
            "https://images.pokemontcg.io/sm10/73.png",
            "https://images.pokemontcg.io/sm11/252.png",
            "https://images.pokemontcg.io/sm10/233.png",
            "https://images.pokemontcg.io/sm10/231.png",
            "https://images.pokemontcg.io/sm10/115.png",
            "https://images.pokemontcg.io/sm10/83.png",
            "https://images.pokemontcg.io/sm11/157.png",
            "https://images.pokemontcg.io/sm10/50.png",
            "https://images.pokemontcg.io/sm115/20.png",
            "https://images.pokemontcg.io/sm11/95.png",
            "https://images.pokemontcg.io/sm10/202.png",
            "https://images.pokemontcg.io/sm10/158.png",
            "https://images.pokemontcg.io/sm11/61.png",
            "https://images.pokemontcg.io/sm10/148.png",
            "https://images.pokemontcg.io/sm10/212.png",
            "https://images.pokemontcg.io/sm10/67.png",
            "https://images.pokemontcg.io/sm11/105.png",
            "https://images.pokemontcg.io/sm10/28.png",
            "https://images.pokemontcg.io/sm11/210.png",
            "https://images.pokemontcg.io/sm10/232.png",
            "https://images.pokemontcg.io/sm11/195.png",
            "https://images.pokemontcg.io/sm10/7.png",
            "https://images.pokemontcg.io/sm10/196.png",
            "https://images.pokemontcg.io/sm11/17.png",
            "https://images.pokemontcg.io/sm10/54.png",
            "https://images.pokemontcg.io/sm11/163.png",
            "https://images.pokemontcg.io/sm115/30.png",
            "https://images.pokemontcg.io/sm11/56.png",
            "https://images.pokemontcg.io/sm11/65.png",
            "https://images.pokemontcg.io/sm11/219.png",
            "https://images.pokemontcg.io/sm10/62.png",
            "https://images.pokemontcg.io/sm11/23.png",
            "https://images.pokemontcg.io/sm115/41.png",
            "https://images.pokemontcg.io/sm115/61.png",
            "https://images.pokemontcg.io/sm11/206.png",
            "https://images.pokemontcg.io/sm11/78.png",
            "https://images.pokemontcg.io/sm10/108.png",
            "https://images.pokemontcg.io/sm11/240.png",
            "https://images.pokemontcg.io/sm11/2.png",
            "https://images.pokemontcg.io/sm11/166.png",
            "https://images.pokemontcg.io/sm11/34.png",
            "https://images.pokemontcg.io/sm11/203.png",
            "https://images.pokemontcg.io/sm11/135.png",
            "https://images.pokemontcg.io/sm10/12.png",
            "https://images.pokemontcg.io/sm11/30.png",
            "https://images.pokemontcg.io/sm10/96.png",
            "https://images.pokemontcg.io/sm115/44.png",
            "https://images.pokemontcg.io/sm10/110.png",
            "https://images.pokemontcg.io/sm11/189.png",
            "https://images.pokemontcg.io/sm10/183.png",
            "https://images.pokemontcg.io/sm11/143.png",
            "https://images.pokemontcg.io/sm11/200.png"
    )

    fun stackCards(): (List<PokemonCard>) -> List<StackedPokemonCard> {
        return {
            val map = ArrayMap<PokemonCard, Int>(it.size)
            it.forEach { card ->
                val count = map[card] ?: 0
                map[card] = count + 1
            }
            map.map { StackedPokemonCard(it.key, it.value) }
                    .sortedBy { card -> card.card.nationalPokedexNumber }
        }
    }

    fun stackCards(cards: List<PokemonCard>, collection: List<CollectionCount>): List<StackedPokemonCard> {
        val map = ArrayMap<PokemonCard, Int>(cards.size)
        val collectionMap = ArrayMap<PokemonCard, Int>(cards.size)
        cards.forEach { card ->
            val count = map[card] ?: 0
            map[card] = count + 1
            collectionMap[card] = collection.find { it.id == card.id }?.count ?: 0
        }

        return map.map {
            val collectionCount = collectionMap[it.key] ?: 0
            StackedPokemonCard(it.key, it.value, collectionCount)
        }.sortedBy { card -> card.card.nationalPokedexNumber }
    }
}

fun List<PokemonCard>.stack(): List<StackedPokemonCard> = CardUtils.stackCards().invoke(this)

fun List<PokemonCard>.stack(collection: List<CollectionCount>): List<StackedPokemonCard> = CardUtils.stackCards(this, collection)

fun List<StackedPokemonCard>.unstack(): List<PokemonCard> = this.flatMap { stack ->
    (0 until stack.count).map { stack.card.copy() }
}

/**
    [C] olorless (Normal, Flying, older cards also Dragon)
    [F] ighting (Fighting, Rock, Ground)
    [L] ightning (Electric)
    [G] rass (Grass, Bug, older cards also Poison)
    [P] sychic (Psychic, Ghost, Poison)
    [M] etal (Steel)
    [W] ater (Water, Ice)
    [D] arkness (Dark)
    Fi[R] e (Fire)
    Drago[N] (Dragon)
    Fair[Y] (Fairy)
 */
fun Type.compact(): String = when(this) {
    Type.COLORLESS -> "C"
    Type.DARKNESS -> "D"
    Type.DRAGON -> "N"
    Type.FAIRY -> "Y"
    Type.FIGHTING -> "F"
    Type.FIRE -> "R"
    Type.GRASS -> "G"
    Type.LIGHTNING -> "L"
    Type.METAL -> "M"
    Type.PSYCHIC -> "P"
    Type.WATER -> "W"
    Type.UNKNOWN -> ""
}

@SuppressLint("DefaultLocale")
fun String.type(): Type = when(this.toUpperCase()) {
    "C" -> Type.COLORLESS
    "D" -> Type.DARKNESS
    "N" -> Type.DRAGON
    "Y" -> Type.FAIRY
    "F" -> Type.FIGHTING
    "R" -> Type.FIRE
    "G" -> Type.GRASS
    "L" -> Type.LIGHTNING
    "M" -> Type.METAL
    "P" -> Type.PSYCHIC
    "W" -> Type.WATER
    else -> Type.UNKNOWN
}

fun String.deserializeTypes(): List<Type> {
    return this.map {
        it.toString().type()
    }
}

fun List<Type>.compactTypes(): String {
    return this.fold("") { acc, type ->
        acc.plus(type.compact())
    }
}

fun String.deserializeEffects(): List<Effect> {
    return this.split(",").map {
        val parts = it.replace("[", "").replace("]", "").split("|")
        Effect(parts[0].type(), parts[1])
    }
}

fun List<Effect>.compactEffects(): String {
    return this.foldIndexed("") { index, acc, effect ->
        val new = acc.plus("[${effect.type.displayName[0].toUpperCase()}|${effect.value}]")
        if (index != this.size - 1) {
            new.plus(",")
        } else {
            new
        }
    }
}

fun String.deserializeCardEffects(): List<io.pokemontcg.model.Effect> {
    return this.split(",").map {
        val parts = it.replace("[", "").replace("]", "").split("|")
        io.pokemontcg.model.Effect(parts[0].type(), parts[1])
    }
}

fun List<io.pokemontcg.model.Effect>.compactCardEffects(): String {
    return this.foldIndexed("") { index, acc, effect ->
        val new = acc.plus("[${effect.type.displayName[0].toUpperCase()}|${effect.value}]")
        if (index != this.size - 1) {
            new.plus(",")
        } else {
            new
        }
    }
}

