echo This script will preform all tasks nessecary to run the bot. Before start make sure config.txt has been filled in.
echo Gradle will also need to be installed for this to run. See installing instructions here: https://gradle.org/install/
read -p "Press enter to continue..." input
echo Cleaning build folder
gradle clean
echo Starting
gradle run