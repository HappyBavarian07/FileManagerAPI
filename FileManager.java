package me.happyBavarian07.API;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.happyBavarian07.regeln.caPlugin;

public class FileManager {
	
	caPlugin plugin;
	
	public FileManager(caPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void createFile(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
		}
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "Could not save File " + f, e);
			}
		} else {
			plugin.getLogger().log(Level.WARNING, "File " + f + " already exists!");
			return;
		}
	}
	
	public File getFile(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
		}
		if(f.exists()) {
			return f;
		}
		return null;
	}
	
	public FileConfiguration getFileConfig(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
		}
		FileConfiguration cfg;
		if(f.exists()) {
			cfg = YamlConfiguration.loadConfiguration(f);
			return cfg;
		}
		return null;
	}
	public void deleteFile(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
		}
		if(f.exists()) {
			f.delete();
			return;
		} else {
			plugin.getLogger().log(Level.WARNING, "File " + f + " doesn't exists!");
		}
		return;
	}
	
	public File[] listFiles(String path) {
		String fullpath = plugin.getDataFolder() + path;
		File folder = new File(fullpath);
		File[] files = new File[folder.listFiles().length];
		for(int i = 0; i < folder.listFiles().length; i++) {
			files[i] = folder.listFiles()[i];
		}
		if(files.length > 0) {
			return files;
		}
		return null;
	}
}
