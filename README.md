After running it, first enter in the cipher text to be solved. Then the absolute location of dictionary to be used. then there are 6 options:

	guess: Input the numbers you want to guess, the multplication one first and then the addition, will output the attempted plaintext.

	showLetterFrequency: Outputs the frequency of each letter in the ciphertext.

	showBestGuess: Attempts to solve the problem by brute force. Manually checks every possibly value and checks the attempted plaintext against a dictionary. Outputs the plaintext with the highest number of matches in the dictionary. Only words of size 3 or greater are considered to minimize false postives.

	showValues: Outputs the number of matched words associated with each possible combination of values for multiplication and addition.

	startOver: Starts the program over. Input a new ciphertext.

	stop: Ends the program.
