
# Hypixel Kotlin Bot

### A discord bot that gets hypixel stats

---
### Setup
To get started with the bot, clone it from git using

```git clone https://github.com/gjoedev/KotlinHypixelBot.git```

Or download the zip. Once downloaded, go to ```src/main/resources/config.txt``` for the config file.

The first value is for your [Discord Bot Token](https://discord.com/developers/applications). The second key is your [Hypixel API key](https://api.hypixel.net/#section/Authentication). The third value is the bot prefix, which can be anything you want

To start the bot, use the start.sh file, or run ```gradle run```

---

### Helping

If you want to help develop the bot, DM me [here](https://discord.com/users/724599899953430629)

---

### Commands

#### Key
| Symbol      | Meaning                        |
| ----------- | ------------------------------ |
| [Argument]  | Argument is not required.      |

##### Hypixel
| Commands | Arguments | Description                         |
| -------- | --------- | ----------------------------------- |
| karma    | Any       | Returns karma of user               |
| login    | Any       | Last login and logout date for user |
| nxp      | Any       | Network xp                          |
| online   | Any       | Checks to see if a player is online |
| rank     | Any       | rank                                |
| socials  | Any       | Gets socials for user               |
| uuid     | Any       | Returns UUID of player              |

#### Utility
| Commands | Arguments | Description               |
| -------- | --------- | ------------------------- |
| Help     | [Command] | Display a help menu.      |
| botinfo  |           | Information about the bot |
| ping     |           | Pings bot and API         |
