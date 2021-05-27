package com.mes.shiestywave.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.ArtistCover
import com.mes.shiestywave.data.data.local.entity.FeaturedArtist
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.domain.usecase.ArtistUseCase
import com.mes.shiestywave.domain.usecase.SongUseCase
import com.mes.shiestywave.utils.safeLaunchWithIo
import kotlinx.coroutines.flow.first
import org.joda.time.DateTime

class SongViewModel(
    private val songUseCase: SongUseCase
) : ViewModel() {

    fun getSongs() = songUseCase.allSongs()
    suspend fun getSong(id: String) = songUseCase.get(id = id)
}

class ArtistViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {
    fun getArtists() = artistUseCase.allArtists()
    suspend fun getArtist(id: String) = artistUseCase.get(id)
}

class HomeViewModel(
    private val songUseCase: SongUseCase,
    private val artistUseCase: ArtistUseCase,
) : ViewModel() {

    init {
        val featuredArtists = mutableListOf<FeaturedArtist>()
        val artistCovers = mutableListOf<ArtistCover>()
        val artists = mutableListOf<Artist>()
        val songs = mutableListOf<Song>()
        viewModelScope.safeLaunchWithIo {
            songUseCase.nukeDb()

            artists.addAll(
                listOf(
                    Artist("Drake").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://c8d8q6i8.stackpathcdn.com/wp-content/uploads/2020/12/Drake.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://upload.wikimedia.org/wikipedia/commons/2/28/Drake_July_2016.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Travis Scott").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://media.gq.com/photos/5f3aa9738f9d96b932abcdc3/4:3/w_1999,h_1499,c_limit/travis-scott-gq-cover-september-2020-a.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://static.highsnobiety.com/thumbor/omVF9r0scwUDzFM84cTB-VC-Zbg=/1600x1067/static.highsnobiety.com/wp-content/uploads/2020/02/21154021/travis-scott-pop-smoke.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("A\$ap Rocky").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://otakukart.com/wp-content/uploads/2021/05/as2.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://static.billboard.com/files/media/asap-rocky-2018-bxt-billboard-1548-compressed.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("The Midnight").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://cdns-images.dzcdn.net/images/artist/80651487a34500c13a4f51fd6d1731a6/500x500.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://images-na.ssl-images-amazon.com/images/I/81HoomIWTPL._AC_UL600_SR600,600_.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Gucci Mane").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://static.highsnobiety.com/thumbor/7P-sjxYy4L4gICZJ0CSNFmncsQo=/1600x1067/static.highsnobiety.com/wp-content/uploads/2019/10/04154838/gucci-mane-megan-thee-stallion-01.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Pooh Shiesty").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://dehayf5mhw1h7.cloudfront.net/wp-content/uploads/sites/1300/2021/05/11102827/gettyimages-1312366922-594x594-1.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://static.stereogum.com/uploads/2021/02/Pooh-Shiesty-1612900994.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Big 30").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://jamznet.com/wp-content/uploads/2020/08/hqdefault-25-1.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Lil Durk").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://resources.tidal.com/images/0014230f/5e68/4daa/9247/79d66e273132/750x750.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://specials-images.forbesimg.com/imageserve/5eb07b65a7e7a5000720125b/960x0.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Lil Baby").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://media.gq.com/photos/5f6b7b4976d092604d0dec36/master/w_2500,h_3750,c_limit/lil-baby-gq-october-2020-05.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://static01.nyt.com/images/2020/06/23/arts/21popcast/21popcast-mediumSquareAt3X.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                    Artist("Migos").apply {
                        artistCovers.addAll(
                            listOf(
                                ArtistCover(
                                    uri = "https://pyxis.nymag.com/v1/imgs/065/d31/375c3989f92ebaef5eeb0d0c81b6fdbd9d-06-migos-1.rsquare.w700.jpg",
                                    artistId = this.id
                                ),
                                ArtistCover(
                                    uri = "https://static.billboard.com/files/media/06-migos-bb7-jkdlsi-billboard-alzi-2017-1548-compressed.jpg",
                                    artistId = this.id
                                )
                            )
                        )
                    },
                )
            )
            songs.addAll(
                listOf(
                    Song(
                        "SICKO MODE", artists[1].id,
                        "https://media.pitchfork.com/photos/5b649fcc0b743703a498201f/1:1/w_500/Travis%20Scott%20_%20Astroworld_Cover%20Art.jpg",
                        "https://youtu.be/6ONRf7h3Mdk",
                        DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                    Song(
                        "I'm Upset!", artists[0].id,
                        "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fbryanrolli%2Ffiles%2F2018%2F06%2Fdrake_im_upset.jpg",
                        "https://youtu.be/rIhx2wZ8jnA", DateTime.now()
                    ),
                    Song(
                        "Fashion Killa", artists[2].id,
                        "https://assets.teenvogue.com/photos/55832201c3f29bdf1f2b631d/16:9/w_2560%2Cc_limit/entertainment-music-2013-09-rihanna-asap-rocky-fashion-killa-main.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Gloria", artists[3].id,
                        "https://f4.bcbits.com/img/a1146583911_10.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ),
                    Song(
                        "Shit Crazy", artists[4].id,
                        "https://linkstorage.linkfire.com/medialinks/images/6d4a51e9-418c-4551-a760-98ac85cad67f/artwork-440x440.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[6].id),
                            )
                        )
                    },
                    Song(
                        "Back In Blood", artists[5].id,
                        "https://static.hiphopdx.com/2021/02/Screen-Shot-2021-02-05-at-12.30.19-PM-e1612546278629.png",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[7].id),
                            )
                        )
                    },
                    Song(
                        "Allegations", artists[6].id,
                        "https://images.genius.com/989f620dc5adbb16348fb23f496901c0.1000x999x1.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[5].id),
                            )
                        )
                    },
                    Song(
                        "Finesse Out The Gang Way", artists[7].id,
                        "https://i.ytimg.com/vi/m2x-r1Cyrt0/maxresdefault.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[8].id),
                            )
                        )
                    },
                    Song(
                        "Yes Indeed", artists[8].id,
                        "https://static.hiphopdx.com/2018/05/Drake-IG-827x620.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                    Song(
                        "Walk It Like I Talk It", artists[9].id,
                        "https://thesource.com/wp-content/uploads/2018/03/Walk-It-Talk-It-Video-Migos-Drake.jpg",
                        "https://youtu.be/6ONRf7h3Mdk", DateTime.now()
                    ).also {
                        featuredArtists.addAll(
                            listOf(
                                FeaturedArtist(it.id, artists[0].id),
                            )
                        )
                    },
                )
            )

            songUseCase.save(songs).first()
            artistUseCase.save(artists).first()
            artistUseCase.saveFeaturedArtists(featuredArtists).first()
            artistUseCase.saveArtistCovers(artistCovers).first()
        }
    }

    fun getArtists() = artistUseCase.allArtists()
    suspend fun getArtist(id: String) = artistUseCase.get(id)

    fun getSongs() = songUseCase.allSongs()
    suspend fun getSong(id: String) = songUseCase.get(id = id)
}
