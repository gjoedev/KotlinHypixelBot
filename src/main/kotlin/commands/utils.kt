package commands

import me.jakejmattson.discordkt.api.dsl.commands
import dev.kord.core.behavior.channel.createEmbed
import me.jakejmattson.discordkt.api.arguments.AnyArg
import me.jakejmattson.discordkt.api.extensions.addField
import commands.api

fun utils() = commands("Utility"){
    command("ping"){
        description = "Pings bot and API"
        execute{
            val mts: Long? = message?.timestamp?.toEpochMilliseconds()
            val rts = System.currentTimeMillis()
            val ping = mts?.minus(rts)
            message?.channel?.createEmbed {
                title="Ping"
                description="Ping to Bot"
                addField("Bot ping","${ping}ms")
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