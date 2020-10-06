package org.mcnative.loader.bridged.bungeecord;

import net.pretronic.libraries.document.Document;
import net.pretronic.libraries.document.EmptyDocument;
import net.pretronic.libraries.plugin.description.PluginDescription;
import net.pretronic.libraries.plugin.description.PluginVersion;
import net.pretronic.libraries.plugin.description.dependency.Dependency;
import net.pretronic.libraries.plugin.description.mainclass.MainClass;
import net.pretronic.libraries.plugin.description.mainclass.SingleMainClass;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class BungeeCordHybridPluginDescription implements PluginDescription{

    private final net.md_5.bungee.api.plugin.PluginDescription original;
    private final String messageModule;

    public BungeeCordHybridPluginDescription(net.md_5.bungee.api.plugin.PluginDescription original,String messageModule) {
        this.original = original;
        this.messageModule = messageModule;
    }

    @Override
    public String getName() {
        return original.getName();
    }

    @Override
    public String getCategory() {
        return "General";
    }

    @Override
    public String getDescription() {
        return original.getDescription();
    }

    @Override
    public String getAuthor() {
        return original.getAuthor();
    }

    @Override
    public String getWebsite() {
        return null;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public PluginVersion getVersion() {
        return new PluginVersion(original.getVersion(),1,0,0,0,"");
    }

    @Override
    public PluginVersion getLatestVersion() {
        return null;
    }

    @Override
    public void setLatestVersion(PluginVersion pluginVersion) {
        //Unused
    }

    @Override
    public String getMessageModule() {
        return messageModule;
    }

    @Override
    public MainClass getMain() {
        return new SingleMainClass(original.getMain());
    }

    @Override
    public Collection<Dependency> getDependencies() {
        return Collections.emptyList();//@Todo implement
    }

    @Override
    public Collection<String> getProviders() {
        return Collections.emptyList();
    }

    @Override
    public Document getProperties() {
        return EmptyDocument.newDocument();
    }

}
