package com.grishma.searchimage.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Data class ImageSearch
 */
data class ImageSearch(
    var `data`: List<Data>?,
    var status: Int?,
    var success: Boolean?
) {
    @Entity(tableName = "data")
    data class Data(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("account_id")
        var accountId: Int?,
        @SerializedName("account_url")
        var accountUrl: String?,

        @SerializedName("ad_type")
        var adType: Int?,
        @SerializedName("ad_url")
        var adUrl: String?,
        var animated: Boolean?,
        var bandwidth: Long?,
        @SerializedName("comment_count")
        var commentCount: Int?,
        var cover: String?,
        @SerializedName("cover_height")
        var coverHeight: Int?,
        @SerializedName("cover_width")
        var coverWidth: Int?,
        var datetime: Int?,
        var downs: Int?,
        var edited: Int?,
        var favorite: Boolean?,
        @SerializedName("favorite_count")
        var favoriteCount: Int?,
        //var gifv: String,
        @SerializedName("has_sound")
        var hasSound: Boolean?,
        var height: Int?,
        //var hls: String,
        var id: String?,
        var images: List<Image>?,
        @SerializedName("images_count")
        var imagesCount: Int?,
        @SerializedName("in_gallery")
        var inGallery: Boolean?,
        @SerializedName("in_most_viral")
        var inMostViral: Boolean?,
        @SerializedName("include_album_ads")
        var includeAlbumAds: Boolean?,
        @SerializedName("is_ad")
        var isAd: Boolean?,
        @SerializedName("is_album")
        var isAlbum: Boolean?,
        var layout: String?,
        var link: String?,
        var looping: Boolean?,

        //var mp4: String,
        @SerializedName("mp4_size")
        var mp4Size: Int?,
        var nsfw: Boolean?,
        var points: Int?,
        var privacy: String?,
        var score: Int?,
        var section: String?,
        var size: Int?,
        var tags: List<Tag>?,
        var title: String?,
        var topic: String?,
        @SerializedName("topic_id")
        var topicId: Int?,
        //var type: String,
        var ups: Int?,
        var views: Int?,
        var width: Int?,
        var comment : String?
    ) : Serializable{

        data class Image(
            @SerializedName("account_id")
            var accountId: Any?,
            @SerializedName("account_url")
            var accountUrl: Any?,
            @SerializedName("ad_type")
            var adType: Int?,
            @SerializedName("ad_url")
            var adUrl: String?,
            var animated: Boolean?,
            var bandwidth: Double?,
            @SerializedName("comment_count")
            var commentCount: Any,
            var datetime: Int?,
            var description: String?,
            var downs: Any?,
            var edited: String?,
            var favorite: Boolean?,
            @SerializedName("favorite_count")
            var favoriteCount: Any?,
            var gifv: String?,
            @SerializedName("has_sound")
            var hasSound: Boolean?,
            var height: Int?,
            var hls: String?,
            var id: String?,
            @SerializedName("in_gallery")
            var inGallery: Boolean?,
            @SerializedName("in_most_viral")
            var inMostViral: Boolean?,
            @SerializedName("is_ad")
            var isAd: Boolean?,
            var link: String?,
            //var mp4: String,
            @SerializedName("mp4_size")
            var mp4Size: Int?,
            var nsfw: Any?,
            var points: Any?,
            var score: Any?,
            var section: Any?,
            var size: Int?,
            var tags: List<Any>?,
            var title: Any?,
            var type: String?,
            var ups: Any?,
            var views: Int?,
            var vote: Any?,
            var width: Int?
        ) :Serializable

        data class Processing(
            var status: String
        )

        data class Tag(
            var accent: String,
            @SerializedName("background_hash")
            var backgroundHash: String,
            @SerializedName("background_is_animated")
            var backgroundIsAnimated: Boolean,
            var description: String,
            @SerializedName("description_annotations")
            var descriptionAnnotations: DescriptionAnnotations,
            @SerializedName("display_name")
            var displayName: String,
            var followers: Int,
            var following: Boolean,
            @SerializedName("is_promoted")
            var isPromoted: Boolean,
            @SerializedName("is_whitelisted")
            var isWhitelisted: Boolean,
            @SerializedName("logo_destination_url")
            var logoDestinationUrl: Any,
            @SerializedName("logo_hash")
            var logoHash: Any,
            var name: String,
            @SerializedName("thumbnail_hash")
            var thumbnailHash: Any,
            @SerializedName("thumbnail_is_animated")
            var thumbnailIsAnimated: Boolean,
            @SerializedName("total_items")
            var totalItems: Int
        ) : Serializable{
            class DescriptionAnnotations(
            )
        }
    }
}