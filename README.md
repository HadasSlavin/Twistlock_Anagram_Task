# Twistlock_Anagram_Task
The general idea is to create Hash Map between each permutation and the list with the words that exist from the same permutation.
To build the map, I sort every word from "words_clean.txt" file. If a key map exists with the sorted word, the original word is added 
to its value, else - another key is created from it.
The mapping is built before the server goes up.

#similar api: 
If there is a key mapping with the word from the query in its sorted form, the service will return the value for this mapping (the value 
is a list of anagrams for that word)
If theere is no key with the word from the query in its sorted form the service will return empty list.

#Running the program:
To run the program from the command line, download target/Twistlock_Anagram_Task-jar-with-dependencies.jar
navigate to the jar directory and use the following command:

java -jar Twistlock_Anagram_Task-jar-with-dependencies.jar
