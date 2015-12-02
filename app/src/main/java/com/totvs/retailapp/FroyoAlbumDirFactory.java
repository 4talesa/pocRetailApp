package com.totvs.retailapp;

import java.io.File;

import android.os.Environment;

/**
 * Created by rond.borges on 01/12/2015.
 */

public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

    @Override
    public File getAlbumStorageDir(String albumName) {
        // TODO Auto-generated method stub
        return new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ),
                albumName
        );
    }
}