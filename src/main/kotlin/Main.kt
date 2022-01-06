import com.ryanhcode.kopixel.KoPixelAPI
import me.jakejmattson.discordkt.api.dsl.bot
import java.io.File

var cfgarray:List<String>? = null
fun main(){
    ReadConfig()
    println(cfgarray?.get(0).toString())
    bot("${cfgarray?.get(0).toString()}"){
        prefix{cfgarray?.get(2).toString()}
        configure {
            allowMentionPrefix = true
            commandReaction = null
        }
        presence {
            watching("you. you are not safe")
        }
    }

}

fun ReadConfig(){
    val path = System.getProperty("user.dir")
    val configfile:String = File("$path/src/main/resources/config.txt").readText()
    cfgarray = configfile.split("/")
}

