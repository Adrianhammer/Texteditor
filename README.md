# IS-211 Texteditor mandatory 1

### Authors: Adrian Hammer & Adel Hodzalari

#### In this assignment we have been instructed to create a text editor that should be capable of inserting, overwriting and deleting text anywhere in the document.

#### Keybindings

- ALT needs to be pressed when navigating around with arrow keys and line shifting (⎇ + ↑ ↓ → ← ⏎)
- Backspace to delete previous character (Behind cursors) ⌫
- Delete to delete next character (In front of cursor) ⌦
- Shift to write in capital letters ⇧
- Caps Lock to toggle between capital and lowercase letters ⇧
- Space to insert space between characters ⎵

# Functionality

- Characters supported: A-Z|0-1|a-z|§-?|Ø-Å|ø-å|
- The text editor works through the action classes that take an ActionEvent as argument and calls the corresonding methods in document class
- In the text editor you are able to move the cursor Left, Right, Up and Down with the keybindings above, this calls the arrowKeyAction class
- In the text editor you can delete both in front and behind the cursor with the corresponding keybindings above, this will call the deleteNext & deletePrev Action    classes
- The text editor has manual line shift implemted, meaning that if you press ⇧ + ⏎ you will start at the beginning of the new incremented row
- The text editor has automatic line shift implemented, meaning that when you reach the end of the row, you will start at the beginning in the new incremented row

# Datastructure
After thorough searching on the internet for the best soloutions to which datastructure is beneficial in our assignment, aswell as consulting the T.A and other key sources. We discovered that the best soloution is to use a Doubly Linked List as our datastructre. The reason for this is that the linked list has faster character insertion both at the beggining and the end of the list compared to a normal arraylist (O(1)). This is due to the fact that each node in a linked list points to either the previous node, or the next node when it is created.

# Known Bugs
- After pressing backspace and then trying to insert new character will result in the first remaining character being replaced by the new one 
- The cursor seems to be leaving traces of previous position when you move the cursor manually with the arrowKeys (Left, Right, Up and Down)
- And probably more that we have not discovered yet - Happy hunting.
