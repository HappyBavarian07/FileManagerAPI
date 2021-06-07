package me.happyBavarian07.API;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.happyBavarian07.regeln.caPlugin;

public class FileManager {
	
	caPlugin plugin;
	Logger log;
	
	public FileManager(caPlugin plugin) {
		this.plugin = plugin;
		this.log = plugin.getLogger();
	}
	
	public void createFile(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
			f.getParentFile().mkdir();
		}
		if(!f.exists()) {
			try {
				log.log(Level.FINE, "Saved " + f + " successfully!");
				f.createNewFile();
			} catch (IOException e) {
				log.log(Level.SEVERE, "Could not save File " + f, e);
			}
		} else {
			log.log(Level.WARNING, "File " + f + " already exists!");
			return;
		}
	}
	
	public void saveConfig(String path, String name, String fileending) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(getFile(path, name, fileending));
		File file = getFile(path, name, fileending);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
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
			log.log(Level.FINE, "Saved " + f + " successfully!");
			return f;
		}
		return null;
	}
	
	public FileConfiguration getConfig(String path, String name, String fileending) {
		File f;
		if(path == "") {
			f = new File(plugin.getDataFolder(), name + "." + fileending);
		} else {
			f = new File(plugin.getDataFolder() + "/" + path, name + "." + fileending);
		}
		FileConfiguration cfg;
		if(f.exists()) {
			cfg = YamlConfiguration.loadConfiguration(f);
			log.log(Level.FINE, "Loaded FileConfig for File " + f + " successfully!");
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
			log.log(Level.FINE, "Deleted " + f + " successfully!");
			f.delete();
			return;
		} else {
			log.log(Level.WARNING, "File " + f + " doesn't exists!");
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
