package com.greenblitz.refapp.feature

enum class Foul {
    /**
     * types of fouls
     */
    Foul{
        override fun toString():String{
            return "Foul"
        }
    }, Penelty{
        override fun toString():String{
            return "Foul"
        }
    }, Disable{
        override fun toString():String{
            return "Disable"
        }
    };
}