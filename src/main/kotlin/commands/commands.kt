package commands
import me.jakejmattson.discordkt.api.dsl.commands
import com.ryanhcode.kopixel.player.SocialMediaLinks
import dev.kord.core.behavior.channel.createEmbed
import me.jakejmattson.discordkt.api.arguments.AnyArg
import me.jakejmattson.discordkt.api.extensions.addField
import java.lang.IllegalStateException
import com.ryanhcode.kopixel.KoPixelAPI.Companion.NewKoPixelAPI
import java.io.File

val apikey = getKey()
val api = NewKoPixelAPI(apikey)


fun status() = commands("Hypixel"){
    command("online"){
        description = "Checks to see if a player is online"
        execute(AnyArg){
            val player = args.first
            try {
                api.getSync(player)
            } catch (e: IllegalStateException){
                message?.channel?.createEmbed {
                    title = "Error"
                    description = "Encountered an error while processing request"
                    addField("Supposed Cause", "User does not exist")
                    addField("Error info", "```${e.toString()}```")
                }
                return@execute
            } catch(e: NullPointerException){
                message?.channel?.createEmbed {
                    title = "Error"
                    description = "Encountered an error while processing request"
                    addField("Supposed Cause", "I have no idea")
                    addField("Error info", "```${e.toString()}```")
                }
                return@execute
            }
            val online = api.getSync(player).online()
            var fonline:String?
            var cgame:String? = null
            if(online){fonline="Online"}else{fonline="Offline"}

            if(online){cgame = api.getSync(player).mostRecentGameType}

            message?.channel?.createEmbed {
                title = "Player Status"
                description = "Checks weather a player is online or not"
                addField("Player: " + api.getSync(player).name, "Status: $fonline")
                if(online){addField("Most recent game", "$cgame")}
            }
        }
    }
    command("rank"){
        description = "rank"
        execute(AnyArg){
            val player = args.first
            var srank:Boolean? = null
            try{
                api.getSync(player)
            }catch(e: java.lang.IllegalStateException){
                message?.channel?.createEmbed {
                    title= "Error"
                    description="Encountered an error while processing request"
                    addField("Supposed Cause", "User does not exist")
                    addField("Error info", "```${e.toString()}```")
                }
            }
            try{
                api.getSync(player).rank
            }catch(e:java.lang.NullPointerException){
                try{
                    api.getSync(player).specialRank
                }catch(e:java.lang.NullPointerException){
                    message?.channel?.createEmbed {
                        title= "Player rank"
                        description="Returns rank of player"
                        addField("Player: ${api.getSync(player).name}", "Rank: None")
                    }
                    srank= false
                    return@execute
                }
                srank = true
            }
            var rr:String? = null
            if(srank == false){
                val rank = api.getSync(player).rank
                rr = returnreadablerank(rank.toString())
            }
            if(srank == true){
                val rank = api.getSync(player).specialRank
                rr = returnreadablerank(rank.toString())
            }

            message?.channel?.createEmbed {
                title = "Player rank"
                description = "Returns rank of player"
                addField("Player: ${api.getSync(player).name}", "Rank: $rr")
            }
        }
    }
    command("nxp"){
        description = "Network xp"
        execute(AnyArg){
            val player = args.first
            val xp = api.getSync(player.toString()).networkXP
            message?.channel?.createEmbed {
                title = "Network XP"
                description = "Shows network XP for player"
                addField("Player: ${api.getSync(player.toString()).name}", "Network XP: $xp")
            }
        }
    }
    command("socials"){
        description = "Gets socials for user"
        execute(AnyArg){
            val player = args.first
            val socials: SocialMediaLinks = api.getSync(player.toString()).socials
            if(socials == null){
                message?.channel?.createEmbed {
                    title="Socials"
                    description="Connected socials for user ${api.getSync(player).name}"
                    addField("No connected socials", "This user has not connected any socials to their account")
                }
                return@execute
            }
            message?.channel?.createEmbed {
                title="Socials"
                description="Connected socials for user ${api.getSync(player).name}"
                if(socials.youtube != null) addField("Youtube:", socials.youtube.toString())
                if(socials.discord != null) addField("Discord:", socials.discord.toString())
                if(socials.instagram != null) addField("Instagram:", socials.instagram.toString())
                if(socials.twitch != null) addField("Twitch:", socials.twitch.toString())
                if(socials.twitter != null) addField("Twitter:", socials.twitter.toString())
                if(socials.forums != null) addField("Forums:", socials.forums.toString())
            }
        }
    }
    command("login"){
        description="Last login and logout date for user"
        execute(AnyArg){
            val player = args.first
            val llogin = api.getSync(player.toString()).lastLogin.time / 1000
            val llogout = api.getSync(player.toString()).lastLogout.time / 1000
            message?.channel?.createEmbed {
                title="Login info"
                description="Last login/logout times for user"
                addField("Last Login", "<t:$llogin:f>")
                addField("Last Logout", "<t:$llogout:f>")
            }
        }
    }
    command("karma"){
        description="Returns karma of user"
        execute(AnyArg){
            try {
                api.getSync(args.first)
            } catch (e: IllegalStateException){
                message?.channel?.createEmbed {
                    title = "Error"
                    description = "Encountered an error while processing request"
                    addField("Supposed Cause", "User does not exist")
                    addField("Error info", "```${e.toString()}```")
                }
                return@execute
            }
            val k = api.getSync(args.first).karma.toString()
            message?.channel?.createEmbed {
                title="Karma"
                description="Karma of user"
                addField("User: ${api.getSync(args.first).name}", "Karma: $k")
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
}
fun returnreadablerank(r: String): String?{
    val rank = r.toString()
    if(rank == "MVP_PLUS") return("MVP Plus")
    if(rank == "MVP") return("MVP")
    if(rank == "VIP") return("VIP")
    if(rank == "VIP_PLUS") return("VIP Plus")
    if(rank == "ADMIN") return("Admin")
    if(rank == "MODERATOR") return("Moderator")
    if(rank == "HELPER") return("Helper")
    if(rank == "YOUTUBER") return("Youtuber")
    if(rank == "NORMAL") return("Normal")
    return("None")
}
fun getKey(): String{
    val path = System.getProperty("user.dir")
    val configfile:String = File("$path/src/main/resources/config.txt").readText()
    val cfgarray = configfile.split("/")
    return cfgarray[1]
}