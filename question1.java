//-------------------------------------------------------
//Assignment 3
//Written by: Nicholas Yiphoiyen 40117237
//For COMP248 Section EC – Fall 2020
//--------------------------------------------------------

import java.util.Random;

public class question1
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the password generator game");

        final String[][][] book = {
                {
                    {"ALICE was beginning to get very tired of sitting by her sister on the\n",
                        "bank, and of having nothing to do. Once or twice she had peeped into the\n",
                        "book her sister was reading, but it had no pictures or conversations in\n",
                        "it, \"and what is the use of a book,\" thought Alice, \"without pictures or\n",
                        "conversations?\"\n"},
                    {"So she was considering in her OWN mind (as well as she could, for the\n",
                        "day made her feel very sleepy and stupid), whether the pleasure of\n",
                        "making a daisy-chain would be worth the trouble of getting up and\n",
                        "picking the daisies, when suddenly a White Rabbit with pink eyes ran\n",
                        "close by her.\n"},
                    {"There was nothing so very remarkable in that, nor did Alice think it so\n",
                        "very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
                        "dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
                        "out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
                        "started to her feet, for it flashed across her mind that she had never\n",
                        "before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
                        "out of it, and, burning with curiosity, she ran across the field after\n",
                        "it and was just in time to see it pop down a large rabbit-hole, under\n",
                        "the hedge. In another moment, down went Alice after it!"}
                },
                {
                    {"\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n",
                        "telescope!\"\n"},
                    {"And so it was indeed! She was now only ten inches high, and her face\n",
                        "brightened up at the thought that she was now the right size for going\n",
                        "through the little door into that lovely garden.\n"},
                    {"After awhile, finding that nothing more happened, she decided on going\n",
                        "into the garden at once; but, alas for poor Alice! When she got to the\n",
                        "door, she found she had forgotten the little golden key, and when she\n",
                        "went back to the table for it, she found she could not possibly reach\n",
                        "it: she could see it quite plainly through the glass and she tried her\n",
                        "best to climb up one of the legs of the table, but it was too slippery,\n",
                        "and when she had tired herself out with trying, the poor little thing\n",
                        "sat down and cried.\n"},
                    {"\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
                        "sharply. \"I advise you to leave off this minute!\" She generally gave\n",
                        "herself very good advice (though she very seldom followed it), and\n",
                        "sometimes she scolded herself so severely as to bring tears into her\n",
                        "eyes.\n"},
                    {"Soon her eye fell on a little glass box that was lying under the table:\n",
                        "she opened it and found in it a very small cake, on which the words \"EAT\n",
                        "ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
                        "Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
                        "makes me grow smaller, I can creep under the door: so either way I'll\n",
                        "get into the garden, and I don't care which happens!\"\n"},
                    {"She ate a little bit and said anxiously to herself, \"Which way? Which\n",
                        "way?\" holding her hand on the top of her head to feel which way she was\n",
                        "growing; and she was quite surprised to find that she remained the same\n",
                        "size. So she set to work and very soon finished off the cake."}
                },
                {
                    {"The King and Queen of Hearts were seated on their throne when they\n",
                        "arrived, with a great crowd assembled about them--all sorts of little\n",
                        "birds and beasts, as well as the whole pack of cards: the Knave was\n",
                        "standing before them, in chains, with a soldier on each side to guard\n",
                        "him; and near the King was the White Rabbit, with a trumpet in one hand\n",
                        "and a scroll of parchment in the other. In the very middle of the court\n",
                        "was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
                        "trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n"},
                    {"The judge, by the way, was the King and he wore his crown over his great\n",
                        "wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
                        "(some were animals and some were birds) I suppose they are the jurors.\"\n"},
                    {"Just then the White Rabbit cried out \"Silence in the court!\"\n"},
                    {"\"HERALD! read the accusation!\" said the King."}
                }
            };
        
        String word[] = new String[3]; //the random words will be placed in this array to be concatenated afterwards
        int count = 0, test = 0, generatedAmount = 0;
        int newLine = 0, Single = 0, Equal = 0, Length = 0, Upper = 0, Lower = 0, Special = 0; //keeping count of the amount of wrongly generated passwords
        String password = "", formattedPassword;
        
        while (generatedAmount < 10000) //loop to generate 10 000 passwords or all upper case letters to break.
        {
            while (test != 5) //loop to make sure that the generated password respects all conditions. When a condition is respected, it increments the variable test by 1
            {
            	test = 0;
                count = 0;
                while (count < 3) //loop to generate a random word
                {
                    Random random = new Random();
                    int randomPage, randomParagraph, randomLine;
       
                    randomPage = random.nextInt(book.length);
                    randomParagraph = random.nextInt(book[randomPage].length);
                    randomLine = random.nextInt(book[randomPage][randomParagraph].length);
       
                    String line = book[randomPage][randomParagraph][randomLine];
                    String possibleWords[] = line.split(" ",line.length());
       
                    int randomWord = random.nextInt(possibleWords.length);
       
                    word[count] = possibleWords[randomWord];
       
                    if (word[count].contains("\n")) //word cannot have \n 
                    {		
                    	newLine++; //error counter
                    	continue; //condition wasn't respected, so going back to generation of word
                    }	
                    else if (word[count].length() ==1) //word cannot be one character 
                    {
                    	Single++; //error counter
                    	continue;
                    }
                    count++;
                }
           
                password = (word[0] + word[1] + word[2]); //concatenation of 3 randomly chosen words to make a password
         
                if (!word[0].equals(word[1]) && !word[0].equals(word[2]) && !word[1].equals(word[2])) //no same word condition
                    test++;
                else
                {
                	Equal++; //error counter
                	continue;//condition wasn't respected, so going back to generation of word
                }
                
                if (password.length() >= 8 && password.length() < 16) //length condition
                    test++;
                else 
                {
                	Length++; //error counter
                	continue;
                }
                	
                if (!password.toUpperCase().equals(password)) //at least 1 lowercase letter
                    test++;
                else
                {
                	Lower++; //error counter
                	continue;
                }
                	
                if (!password.toLowerCase().equals(password)) //at least 1 uppercase letter
                	test++;
                else 
                {
                	Upper++; //error counter
                	continue;
                }
                	
                String specialCharacters = "`-=~!@#$%^&*()_+[]\\;'./,{}|:\"<>?"; //1 and only 1 special character
                int count2 = 0;
                for (int start = 0; start < password.length(); start++)
                {
                    String tempPassword = password.substring(start, start+1);
                    if (specialCharacters.contains(tempPassword))
                        count2++;
                }
                if (count2 == 1) //count2 will equal 1 only when theres only 1 special character in the generated password
                    test++;
                else
                {	
                	Special++; //error counter
                	continue;
                }	
            }
            formattedPassword = String.format("%-16s", password); //formatting to look like a table when printing all the generated passwords
            System.out.print("Password = " + formattedPassword ); 
            System.out.println("\tnewLine = " + newLine + "\tSingle = " + Single + "\tEqual = " + Equal + "\tLength = " + Length + "\tUpper = " + Upper + "\tLower = " + Lower + "\tSpecial = " + Special);
            generatedAmount++;
            if (Lower != 0) //if a password without any lowercase letters is generated, break
            	break;
            test = 0;
            newLine = 0; Single = 0; Equal = 0; Length = 0; Upper = 0; Lower = 0; Special = 0;
       }
       System.out.println("\nTotal passwords generated " + generatedAmount);
       System.out.println("\nThanks for using the password generator game. Good bye.");
    }
}