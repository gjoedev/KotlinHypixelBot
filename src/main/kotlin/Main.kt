import com.ryanhcode.kopixel.KoPixelAPI
import me.jakejmattson.discordkt.api.dsl.bot
import com.ryanhcode.kopixel.KoPixelAPI.Companion.NewKoPixelAPI

fun main(){

    bot("Your token"){
        prefix{"*"}
        configure {
            allowMentionPrefix = true
            commandReaction = null
        }
        presence {
            watching("you. you are not safe")
        }
    }
}