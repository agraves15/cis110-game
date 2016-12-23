/* Names: Alex Graves and Caroline Lu
 * PennKeys: agrav and cmlu
 * Recitation: 219
 *
 * Execution: Creates Simon Says game that user interacts with
 * by clicking on different colored squares and pressing a key
 * to end turn
 *
 */

public class SimonSays {
    //create linked lists for the sequence and user input
    List<String> game = new LinkedList<String>();
    List<String> user = new LinkedList<String>();
    
    //create color variables
    private String r = "r";
    private String g = "g";
    private String b = "b";
    private String y = "y";
    
    //create variable for number of moves
    private int moves = 0;
    
    //create variables for losing lives mechanism
    private boolean lost = false;
    private static int lives = 3;
    
    //create variable for restart mechanism
    private boolean restarted = false;
    
    /* Description: returns size of game list
     * 
     * Input: N/A
     * Output: size of game list
     */
    public int gameSize() {
        return game.size();
    }
    
    /* Description: returns size of user list
     * 
     * Input: N/A
     * Output: size of user list
     */
    public int userSize() {
        return user.size();
    }
    
    /* Description: adds new random color to game sequence
     * 
     * Input: N/A
     * Output: N/A
     */
    public void generate() {
        moves = 0;
        double color = Math.random();
        
        //use the random variable to assign the next color
        if (color > 0.75) {
            game.add(r);
        } else if (color > 0.5) {
            game.add(g);
        } else if (color > 0.25) {
            game.add(b);
        } else {
            game.add(y);
        }
    }
    
    /* Description: checks if user has clicked on a square and adds
     * appropriate color to user linked list; displays number of times
     * user has clicked in this level, and once the user has put in
     * the whole sequence, prompts user to press a key
     * 
     * Input: N/A
     * Output: color that user has clicked on
     */
    public String input() {
        String recorded = "";
        PennDraw.enableAnimation(6);
        
        //check if mouse clicks anywhere
        if (PennDraw.mousePressed()) {
            //get mouse click values
            double mouseX = PennDraw.mouseX();
            double mouseY = PennDraw.mouseY();
            
            //check which square user has clicked in
            if (mouseX > 0.5 && mouseY > 0.5) {
                user.add(r);
                recorded = r;
                PennDraw.setPenColor(204, 0, 0);
                PennDraw.filledRectangle(0.75, 0.75, 0.2, 0.2);
                PennDraw.advance();
                draw();
            } else if (mouseX > 0.5 && mouseY < 0.5) {
                user.add(b);
                recorded = b;
                PennDraw.setPenColor(0, 0, 204);
                PennDraw.filledRectangle(0.75, 0.25, 0.2, 0.2);
                PennDraw.advance();
                draw();
            } else if (mouseX < 0.5 && mouseY > 0.5) {
                user.add(g);
                recorded = g;
                PennDraw.setPenColor(0, 204, 0);
                PennDraw.filledRectangle(0.25, 0.75, 0.2, 0.2);
                PennDraw.advance();
                draw();
            } else if (mouseX < 0.5 && mouseY < 0.5) {
                user.add(y);
                recorded = y;
                PennDraw.setPenColor(204, 204, 0);
                PennDraw.filledRectangle(0.25, 0.25, 0.2, 0.2);
                PennDraw.advance();
                draw();
            } else {
                PennDraw.text(0.5, 0.5, "Please click in one of the squares");
                recorded = null;
            }
            //increment moves
            moves++;
            String userMoves = "Your moves: " + moves;
            PennDraw.text(0.5, 0.97, userMoves);
            if (moves == gameSize()) {
                PennDraw.text(0.5, 0.92, "Press any key!");
            }
            PennDraw.advance();
        }
        return recorded;
    }
    
    /* Description: removes all elements from user list
     * 
     * Input: N/A
     * Output: N/A
     */
    public void resetInput() {
        for (int i = user.size() - 1; i >= 0; i--) {
            user.remove(i);
        }
    }
    
    /* Description: removes all elements from game list
     * 
     * Input: N/A
     * Output: N/A
     */
    public void resetGame() {
        for (int i = game.size() - 1; i >= 0; i--) {
            game.remove(i);
        }
    }
    
    /* Description: compares user list and game list; if at any point
     * the two differ, returns false; returns true if they are identical
     * 
     * Input: N/A
     * Output: whether or not the lists are identical
     */
    public boolean compare() {
        boolean same = true;
        
        //iterate through both game and user
        for (int i = 0; i < game.size() && i < user.size(); i++) {
            if (game.get(i) != user.get(i)) {
                same = false;
                break;
            }
        }
        return same;
    }
    
    /* Description: displays welcome screen at beginning of game
     * 
     * Input: N/A
     * Output: returns true
     */
    public boolean welcome() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(30);
        PennDraw.text(0.5, 0.5, "Welcome to SimonSays");
        PennDraw.setFontSize(20);
        PennDraw.text(0.5, 0.4, "Press any key to start");
        return true;
    }
    
    /* Description: draws game interface
     * 
     * Input: N/A
     * Output: N/A
     */
    public void draw() {
        //draw each square
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledRectangle(0.25, 0.25, 0.25, 0.25);
        PennDraw.setPenColor(PennDraw.BLUE);
        PennDraw.filledRectangle(0.75, 0.25, 0.25, 0.25);
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(0.25, 0.75, 0.25, 0.25);
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledRectangle(0.75, 0.75, 0.25, 0.25);
        
        //draw lives counter in top left corner
        if (lives >= 0) {
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setFontSize(16);
            String lifeCounter = "Lives: " + lives;
            PennDraw.text(0.07, 0.97, lifeCounter);
        }
        
        //draw the level number in top right corner
        String level = "Level: ";
        if (gameSize() == 0) {
            level += 1;
        } else {
            level += gameSize();
        }
        PennDraw.text(0.93, 0.97, level);
    }
    
    /* Description: goes through game list and flashes appropriate
     * color to show user what sequence to repeat; at the end of the
     * sequence, says, "Your turn!"
     * 
     * Input: N/A
     * Output: N/A
     */
    public void flash() {
        //draw screen to clear
        PennDraw.enableAnimation(2);
        draw();
        
        //iterate through game list
        for (int i = 0; i < game.size(); i++) {
            //check which color is in game list and flash accordingly
            if (game.get(i) == "y") {
                PennDraw.setPenColor(255, 255, 153);
                PennDraw.filledRectangle(0.25, 0.25, 0.2, 0.2);
            } else if (game.get(i) == "b") {
                PennDraw.setPenColor(153, 153, 255);
                PennDraw.filledRectangle(0.75, 0.25, 0.2, 0.2);
            } else if (game.get(i) == "g") {
                PennDraw.setPenColor(153, 255, 153);
                PennDraw.filledRectangle(0.25, 0.75, 0.2, 0.2);
            } else if (game.get(i) == "r") {
                PennDraw.setPenColor(255, 153, 153);
                PennDraw.filledRectangle(0.75, 0.75, 0.2, 0.2);
            } else {
                throw new RuntimeException("Game list is invalid.");
            }
            
            //advance animation
            PennDraw.advance();
            
            //draw main game again to cover up flash
            draw();
            PennDraw.advance();
        }
        PennDraw.text(0.5, 0.97, "Your turn!");
    }
    
    /* Description: uses other methods to allow user to interact with
     * and play game
     * 
     * Input: number of lives
     * Output: N/A
     */
    public void play(int lives) {
        PennDraw.enableAnimation(2);
        boolean compare = true;
        boolean start = false;
        
        //if this is the beginning of the game, display welcome screen
        if (lives == 3 && restarted == false) {
            start = welcome();
            PennDraw.advance();
        }
        //wait for user to press a key
        while (start == true) {
            if (PennDraw.hasNextKeyTyped()) {
                PennDraw.nextKeyTyped();
                start = false;
            }
        }
        
        //pause for a frame before starting
        draw();
        PennDraw.advance();
        
        while (compare == true) {
            //if the user just lost a life, don't add to the sequence
            if (lost == false) {
                generate();
            } else {
                lost = false;
            }
            
            //show user the sequence they must repeat
            flash();
            PennDraw.advance();
            
            //record the user's clicks
            boolean mouse = false;
            while (userSize() < gameSize()) {
                mouse = PennDraw.mousePressed();
                if (mouse == true) {
                    String input = input();
                    PennDraw.advance();
                    mouse = false;
                    PennDraw.advance();
                }
            }
            
            //check if user has ended their input by pressing a key
            while (true) {
                if (PennDraw.hasNextKeyTyped()) {
                    PennDraw.nextKeyTyped();
                    compare = compare();
                    break;
                }
            }
            resetInput();
        }
        lose();
    }
    
    /* Description: if the user makes a mistake, takes off a life and
     * says, "You a lost a life!"
     * 
     * Input: N/A
     * Output: N/A
     */
    public void lose() {
        PennDraw.enableAnimation(2);
        moves = 0;
        
        //increment lives
        lives--;
        if (lives == 0) {
            return;
        }
        
        //clear board
        draw();
        
        //draw life-lost message
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(28);
        PennDraw.text(0.5, 0.5, "You lost a life!");
        PennDraw.advance();
        
        //make sequence one shorter if sequence is greater than 1
        lost = true;
        int gameEnd = gameSize() - 1;
        if (gameEnd != 0) {
            game.remove(gameEnd);
        }
    }
    
    /* Description: stops game and says, "GAME OVER" if user
     * loses all lives; prompts user to play again
     * 
     * Input: N/A
     * Output: N/A
     */
    public void gameOver() {
        PennDraw.enableAnimation(2);
        
        //display game over message
        draw();
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(36);
        PennDraw.text(0.5, 0.5, "GAME OVER");
        PennDraw.setFontSize(20);
        PennDraw.text(0.5, 0.4, "Press any key to restart");
        
        PennDraw.disableAnimation();
    }
    
    /* Description: if the user loses all lives and decides to play
     * again, restarts game; recursively calls itself so user can
     * play forever
     * 
     * Input: N/A
     * Output: N/A
     */
    public void restart() {
        //reset variables
        restarted = true;
        lives = 3;
        resetGame();
        
        //wait for user to press key to restart
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                PennDraw.nextKeyTyped();
                break;
            }
        }
        
        //play again
        while (lives > 0) {
            play(lives);
        }
        gameOver();
        restart();
    }
    
    public static void main(String[] args) {
        //REQUIRED CODE FOR GAMEPLAY
        SimonSays simon = new SimonSays();
        
        PennDraw.enableAnimation(2);
        
        while (lives > 0) {
            simon.play(lives);
        }
        simon.gameOver();
        simon.restart();
        
        PennDraw.disableAnimation();
        //END REQUIRED CODE
    }
}