package commands

import com.ryanhcode.kopixel.KoPixelAPI
import me.jakejmattson.discordkt.api.*
import me.jakejmattson.discordkt.api.dsl.commands
import com.ryanhcode.kopixel.player.OnlineStatus
import dev.kord.core.behavior.channel.createEmbed
import kotlinx.datetime.toJavaInstant
import me.jakejmattson.discordkt.api.arguments.AnyArg
import me.jakejmattson.discordkt.api.extensions.addField
import me.jakejmattson.discordkt.api.extensions.addInlineField
import java.lang.Exception
import java.lang.IllegalStateException

fun utils() = commands("Utilities"){
    command("ping"){
        description = "Pings bot and API"
        execute{
            val mts: Long? = message?.timestamp?.toEpochMilliseconds()
            val rts = System.currentTimeMillis()
            val ping = mts?.minus(rts)
            val apiping =
            message?.channel?.createEmbed {
                title="Ping"
                description="Ping to Bot"
                addField("Bot ping","${ping}ms")
            }
        }
    }
    command("uuid"){
        description="Returns UUID of player"
        execute(AnyArg){
            val player = args.first
            try{
                api.getSync(player)
            }catch (e:java.lang.IllegalStateException){
                message?.channel?.createEmbed {
                    title = "Error"
                    description = "Encountered an error while processing request"
                    addField("Supposed Cause", "User does not exist")
                    addField("Error info", "```${e.toString()}```")
                }
                return@execute
            }
            val uuid = api.getSync(player).uuid.toString()
            message?.channel?.createEmbed {
                title="UUID"
                description="Returns UUID of player"
                addField("Player: ${api.getSync(player).name}", "UUID: $uuid")
            }
        }
    }
    command("botinfo"){
        description="Information about the bot"
        execute {
            message?.channel?.createEmbed {
                title="Technical information"
                description="Displays technical info about the bot"
                addField("Source", "https://github.com/gjoedev/KotlinHypixelBot")
                addField("Kotlin", "Bot uses Kotlin as main language, more here https://kotlinlang.org/")
                addField("DiscordKt", "DiscordKt is used for connection to the Discord API, more here https://discordkt.github.io/")
                addField("KoPixel", "KoPixel is used for connection to Hypixel API, more here https://github.com/ryanhcode/KoPixel")
            }
        }
    }
}
