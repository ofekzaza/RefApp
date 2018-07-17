package com.greenblitz.refapp.feature

enum class MessageType {
    AddC{
        override fun toString():String{
            return "AddCargo"
        }
    }, RemC{
        override fun toString():String{
            return "RemoveCargo"
        }
    }, AddF{
        override fun toString():String{
            return "AddFoul"
        }
    }, RemF{
        override fun  toString():String{
            return "RemoveFoul"
        }
    }, Disable{
        override fun toString():String{
            return "Disable"
        }
    }, Enable {
        override fun toString(): String {
            return "Enable"
        }
    }, RemP{
        override fun toString(): String{
            return "RemovePenalty"
        }
    }, AddP{
        override fun toString(): String{
            return "AddPenalty"
        }
    };
}