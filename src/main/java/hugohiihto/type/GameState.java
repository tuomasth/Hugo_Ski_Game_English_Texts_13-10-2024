package hugohiihto.type;

public enum GameState {
    PRE_TITLE,        // 0 - No music
    TITLE_SCREEN,     // 1 - Title and credits screen
    SHOWING_VIDEO,    // 2 - Showing a video
    SKI_GAME,         // 3 - Actual ski game
    REMEMBER_ITEMS,   // 4 - Remember two items
    GAME_OVER,        // 5 - Game over or show score
    VIDEO_TRANSITION  // 6 or higher - Transitioning between videos
}