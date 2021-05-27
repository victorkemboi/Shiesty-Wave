package com.mes.shiestywave.domain.model

import com.mes.shiestywave.data.data.local.entity.Artist
import com.mes.shiestywave.data.data.local.entity.Song
import com.mes.shiestywave.data.data.local.entity.unknownArtist

sealed class SongUiModel {

    class SongModel(
        val song: Song,
        val artist: Artist?,
        val featuredArtists: List<Artist>
    ) : SongUiModel() {
        val title = "${artist?.name ?: unknownArtist.name} - ${song.name}".apply {
            if (featuredArtists.isNotEmpty()) {
                this.plus(
                    " ft ${
                    featuredArtists.joinToString(separator = ", ") {
                        it.name
                    }.removeSuffix(", ")}"
                )
            }
        }
    }

    class SongSeparatorModel(val description: String) : SongUiModel()
}

sealed class ArtistUiModel {

    class ArtistModel(
        val artist: Artist,
    ) : ArtistUiModel()

    class ArtistSeparatorModel(val description: String) : ArtistUiModel()
}

sealed class ArtistSongsUiModel {

    class ArtistSongsModel(
        val artist: Artist,
        val songs: List<SongUiModel.SongModel>
    ) : ArtistSongsUiModel()

    class FeaturedArtistSeparatorModel(val description: String) : ArtistSongsUiModel()
}
