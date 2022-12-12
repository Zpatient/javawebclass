/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugin
 */
import type { IJodit, IPluginSystem, PluginInstance, PluginType, CanPromise, Nullable } from '../../types';
/**
 * Jodit plugin system
 * @example
 * ```js
 * Jodit.plugins.add('emoji2', {
 * 	init() {
 *  	alert('emoji Inited2')
 * 	},
 *	destruct() {}
 * });
 * ```
 */
export declare class PluginSystem implements IPluginSystem {
    private normalizeName;
    private _items;
    private items;
    /**
     * Add plugin in store
     */
    add(name: string, plugin: PluginType): void;
    /**
     * Get plugin from store
     */
    get(name: string): PluginType | void;
    /**
     * Remove plugin from store
     */
    remove(name: string): void;
    /**
     * Public method for async init all plugins
     */
    init(jodit: IJodit): CanPromise<void>;
    /**
     * Returns the promise to wait for the plugin to load.
     */
    wait(name: string): Promise<void>;
    /**
     * Plugin type has disabled requires
     */
    private hasDisabledRequires;
    /**
     * Create instance of plugin
     */
    static makePluginInstance(jodit: IJodit, plugin: PluginType): Nullable<PluginInstance>;
    /**
     * Init plugin if it has not dependencies in another case wait requires plugins will be init
     */
    private initOrWait;
    /**
     * Destroy all plugins before - Jodit will be destroyed
     */
    private addListenerOnBeforeDestruct;
    /**
     * Download plugins
     */
    private load;
    private static loadStyle;
    private static styles;
    /**
     * Call full url to the script or style file
     */
    private static getFullUrl;
    private loadExtras;
}
