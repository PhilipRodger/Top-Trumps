public final class TrumpLog{

    //makes frame view

    // public final MainFrameView view;

    public TrumpLog(final MainFrameView thatView){
        view = thatView;
    }

    // log for when the game is over

    public void gameOverLog(){
        if (view != null){
            view.log("Game Over!");
        }
    }

    public void logDraw(){
        if (view != null){
            view.log("Draw!");
        }
    }

    public void playerLoss(final Player player){
        if (view != null){
            view.log(player.getName() + " Loses this game.");
        }
    }

    public void playerVictory(final Player player){
        if (view != null){
            view.log(player.getName() + "wins!");
        }
    }


    //logs when players wins
    

    //player who won the turn, index of player in array, player array of all players //values of the turn


    public void logPlayerWonTurn(final Player player, final int playerIndex,
                     final Player[] allPlayers, final int[] values ){
                         if (view != null){
                             StringBuilder message = new StringBuilder();
                             message.append(player.getName());
                             message.append("wins, the winning card for this round was: ");
                             message.append(player.lookupTopCard().getName());
                             message.append(player.lookupTopCard().getValues());
                             message.append("(").append(values[playerIndex]);
                             message.append(") wins ");


                             for (int i =0; i < values.length; i++){
                                 if(i != playerIndex){
                                     message.append(allPlayers[i].getName()).append("s");
                                     message.append(allPlayers[i]).lookupTopCard().getName();
                                     message.append("(").append(values[i]).append("), ");

                                 }


                             }
                             view.log(message.substring(0, message,length() -2));


                         }
                         


                     }

                     // logs unknown error

                     public void logUnkownError(final Exception e){

                        if(view != null){
                            view.logError("An unkown error occured.");
                            if(e.getMessage()!=null){
                                view.logError(e.getMessage());
                            }
                        }


                     }
}








