package fsu.bignerdranch.elliottbregni.toodone.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import fsu.bignerdranch.elliottbregni.toodone.Todo;


public class TodoCursorWrapper extends CursorWrapper {
    public TodoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Todo getTodo()  {
        String uuidString = getString(getColumnIndex(TodoDbSchema.TodoTable.Cols.UUID));
        String title = getString(getColumnIndex(TodoDbSchema.TodoTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TodoDbSchema.TodoTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(TodoDbSchema.TodoTable.Cols.SOLVED));
        //This may have to by long


        Todo todo = new Todo(UUID.fromString(uuidString));
        todo.setTitle(title);
        todo.setDate(new Date(date));
        todo.setSolved(isSolved != 0);

        return todo;
    }
}
