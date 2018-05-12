<%-- 
    Document   : index
    Created on : Jan 25, 2013, 4:29:59 PM
    Author     : mheimer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indian Rummy Rules</title>
        <style type="text/css">
            @font-face {
                font-family: 'DejaVu Sans';
                src: url('DejaVuSans.ttf');
            }
            .card, .redcard  {
                font-family: 'DejaVu Sans';
                font-size: 125%;
            }
            .redcard {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Indian Rummy</h1>
        <h2>Also Known As</h2>
        <p>13 Cards Rummy or Paplu</p>
        <h2>Overview</h2>
        <p>Indian Rummy is a rummy variant in which you attempt to win by being the first to build runs and sets using the cards in your hand. It is a cross between Gin Rummy and Rummy 500. Anywhere from two to ten people can play if enough decks of cards are available.</p>
        <h2>Setup</h2>
        <p>For 2-3 players 1 deck is used, 4-6 players requires 2 decks, and 7-10 players requires 3 decks. Card decks with 1 or 2 jokers can be used (jokers are wild). Each player randomly draws a card, the player with the lowest card (Aces are the highest card) deals first. The dealer shuffles the required number of decks and deals thirteen cards (face-down) to each player by dealing one card at a time to each player. The remaining cards are placed in a stack, face down - this is the stock pile. The dealer then creates the discard pile by taking the top card from the stock pile and placing it face up on the table.</p>
        <h2>Playing</h2>
        <p>The player to either the dealer's left or right plays first (clockwise or counter-clockwise rotations can be used). A player begins their turn by picking up the top card from either the discard pile or the stock pile (only one card may be picked up). The player views the cards in their hand (temporarily 14 cards) and selects a single card to discard onto the top of the discard pile. The goal is to arrange the cards in your hand into runs and sets (referred to as melding).</p>
        <p>
        <ul>
            <li>Run - Also known as a sequence or life. A run consists of three or more cards of the same suit in sequential order such as <span class="card">&#x1F0A3;&#x1F0A4;&#x1F0A5;</span>. Aces may be high (after the king) or low (before the two) but may not wrap (<span class="card">&#x1F0AE;&#x1F0A1;&#x1F0A2;</span> is not valid).</li>
            <li>Set - Also known as a book or group. A set consists of three or four cards of the same rank but of different suits such as <span class="card">&#x1F0A7;</span><span class="redcard">&#x1F0B7;&#x1F0C7;</span>.</li>
        </ul>
    </p>
    <p>After discarding play shifts to the next player. If the stock pile is exhausted the discard pile may be shuffled to restart the stock pile (keeping only the top or upcard in the discard pile).</p>
    <h2>Winning</h2>
    <p>To win or rummy, all 13 cards in your hand must be part of either a set or run (no card can belong to more than one set or run). You must have a minimum of 2 runs, one of which must be natural or pure (no wildcards). The first (natural) run is sometimes called "Life 1" and the second run is called "Life 2". Your final discarded card is placed face down to signal that you have gone out. If you show or rummy incorrectly all cards in your hand are considered unmatched and will be scored against you. In the event of an incorrect show the last card discarded (face down) should be placed to the side.</p>
    <h2>Scoring</h2>
    <p>The winner of a round is awarded points from the other players. The J, Q, K, and A are valued at 10 points. Jokers and wildcards are 0 points. All other cards are valued at their face (pip) value. Each losing player's score is reduced by the score of their hand and the winners score is increased by the same amount. If you lose a round but have a natural run (Life 1) then the cards in that run are not counted. If you have both a Life 1 (natural run) and Life 2 (any run) then none of your runs or sets are counted against you.</p>
    <p>The player that was dealt the first card becomes the dealer for the next round. Rounds are played until a winner is determined. Games may be played until several possible milestones:
    <ul>
        <li>Score limit - The first player to reach an agreed upon score (400 or 500 is common) wins.</li>
        <li>A single round - first player to rummy wins.</li>
        <li>Round (deal) limit - The player with the highest score after a fixed number of rounds (12 or 16 is common).</li>
        <li>Time limit - After an agreed amount of time passes the player with the highest score wins.</li>
    </ul>
    When a tie score is possible you can declare all tied players the winner or the player which first reached the high score is declared the winner.
</p>
<h2>Packing or Dropping</h2>
<p>A player may exit a round (pack) if they feel they have a bad hand. If you pack before picking up a card you lose 20 points and your cards are placed at the bottom of the stock pile. If you pack after you have picked up a card during a round then you lose 40 points and your cards are held to the side until the end of the round.</p>
<h2>Variations</h2>
<p>
<ul>
    <li>Exhausted Stock Pile - Instead of re-shuffling the discard pile a game may be declared void.</li>
    <li>Extra Wild - Either before or after dealing the player cards the dealer may select a random card from the stock pile to function as an additional wild card. This card is placed face up and left on the table to remind players of the rank of the additional wild card. It is often rotated 90 degrees and placed under the stock pile. This additional wild card does not have to be used as a wild card, in other words it can be used to create a natural run if the run can be created using the non-wild face value. When used as a wild card it has a value of zero.</li>
    <li>Extra Extra Wild - An additional wild card is selected (see preceding variation) but in addition the to the selected wild card all cards with the same suit one rank above and below are also wild. For example, if the <span class="redcard">&#x1F0B7;</span> is wild, all sevens are wild and the <span class="redcard">&#x1F0B6;</span> and the <span class="redcard">&#x1F0B8;</span> are also wild.</li>
    <li>Opposite Joker - The extra wild card is drawn at the beginning of the round but instead of all cards of the same rank being wild only cards of the same rank and opposite color are wild.</li>
    <li>Double Points Joker - If playing with a randomly selected joker (Extra Wild) and the selected wild card is a joker then all points are doubled.</li>
    <li>Aces Wild - If playing with a randomly selected joker (Extra Wild) and the selected wild card is a joker then all aces are wild.</li>
    <li>Puploo - The extra wild card is drawn at the beginning of the round but instead of that card being wild the card of the same suit and next rank is wild. For example if the seven of hearts is drawn the eight of hearts is wild. Regardless of winning or not the hold of a puploo is awarded points from the other players. Having a single puploo is worth 10 points, two are worth a total of 25 points.</li>
    <li>Jokers - Indian rummy may be played with 0, 1, or 2 jokers (as wild cards) per deck.</li>
    <li>No Rediscarding - A player may not pick up the upcard from the discard pile and then discard that same card.</li>
    <li>Hand Sizing - Instead of dealing 13 cards to each player the dealer may deal 10, 21, 27, or 28 cards. When dealing larger hand sizes 3 decks are used with a maximum of six players.</li>
    <li>Tanala - When using three or more decks a natural run may be created using 3 cards of the same rank and suit.</li>
    <li>Clock Direction - You may play either clockwise or counter-clockwise. All rounds in a game should be played in the same direction.</li>
    <li>Two+ Decks - Never use one deck, 2-6 players use 2 decks and 7-10 use 3 decks.</li>
    <li>Rounded to 5 - All hand scores are rounded to the nearest increment of 5.</li>
    <li>Full Count - All hands score a max penalty of 80. An incorrect show or rummy always scores 80.</li>
    <li>Negative Scoring - No positive scores, the highest score for a round is zero. Players are assigned penalty points to their score (called the count) but the winner does not get a positive total of the penalty points. The winner is the player with the lowest count when play ends. If the game is played to a score limit then the game ends when the first player reaches the agreed upon max penalty count (400 is common).</li>
    <li>First Draw Rummy - If a player rummies after drawing their first card then any players which have not had a chance to draw have their penalty points cut in half.</li>
    <li>Dealt Rummy - If a player can rummy without drawing a single card all other players pay double penalty points.</li>
    <li>Low Pack - Packing before drawing your first card costs 10 penalty points instead of 20.</li>
</ul>
</p>
</body>
</html>
