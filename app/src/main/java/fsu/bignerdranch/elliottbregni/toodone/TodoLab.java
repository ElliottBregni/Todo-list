package fsu.bignerdranch.elliottbregni.toodone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fsu.bignerdranch.elliottbregni.toodone.database.TodoBaseHelper;
import fsu.bignerdranch.elliottbregni.toodone.database.TodoCursorWrapper;
import fsu.bignerdranch.elliottbregni.toodone.database.TodoDbSchema;

/**
 * Created by elliottbregni on 3/20/17.
 */


public class TodoLab {
    private static TodoLab sTodoLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TodoLab get(Context context) {
        if (sTodoLab == null) {
            sTodoLab = new TodoLab(context);
        }
        return sTodoLab;
    }

    private TodoLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TodoBaseHelper(mContext)
                .getWritableDatabase();
    }

    private static ContentValues getContentValues(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(TodoDbSchema.TodoTable.Cols.UUID, todo.getId().toString());
        values.put(TodoDbSchema.TodoTable.Cols.TITLE, todo.getTitle());
        values.put(TodoDbSchema.TodoTable.Cols.DATE, todo.getDate().getTime());
        values.put(TodoDbSchema.TodoTable.Cols.SOLVED, todo.isSolved() ? 1 : 0);

        return values;
    }

    public void addTodo(Todo todo) {
        ContentValues values = getContentValues(todo);

        mDatabase.insert(TodoDbSchema.TodoTable.NAME, null, values);
    }

    public void updateTodo(Todo todo) {
        String uuidString = todo.getId().toString();
        ContentValues values = getContentValues(todo);

        mDatabase.update(TodoDbSchema.TodoTable.NAME, values,
                TodoDbSchema.TodoTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private TodoCursorWrapper queryTodos(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TodoDbSchema.TodoTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new TodoCursorWrapper(cursor);
    }


    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();

        TodoCursorWrapper cursor = queryTodos(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            todos.add(cursor.getTodo());
            cursor.moveToNext();
        }
        cursor.close();

        return todos;
    }

    public Todo getTodo(UUID id) {
        TodoCursorWrapper cursor = queryTodos(
                TodoDbSchema.TodoTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTodo();
        } finally {
            cursor.close();
        }}

    public void deleteTodo(UUID todoId)
    {
        String uuidString = todoId.toString();
        mDatabase.delete(TodoDbSchema.TodoTable.NAME, TodoDbSchema.TodoTable.Cols.UUID + " = ?", new String[] {uuidString});
        }}
