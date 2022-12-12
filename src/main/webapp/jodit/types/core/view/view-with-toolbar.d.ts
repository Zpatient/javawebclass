/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module view
 */

import type { IViewWithToolbar, IToolbarCollection, IDictionary, IPluginButton, IViewOptions } from '../../types';
import { View } from './view';
export declare abstract class ViewWithToolbar extends View implements IViewWithToolbar {
    TOOLBAR: IToolbarCollection;
    readonly toolbar: this['TOOLBAR'];
    private defaultToolbarContainer;
    /**
     * Container for toolbar
     */
    get toolbarContainer(): HTMLElement;
    /**
     * Change panel container
     */
    setPanel(element: HTMLElement | string): void;
    /**
     * Helper for append toolbar in its place
     */
    protected buildToolbar(): void;
    registeredButtons: Set<IPluginButton>;
    private groupToButtons;
    getRegisteredButtonGroups(): IDictionary<string[]>;
    /**
     * Register button for group
     */
    registerButton(btn: IPluginButton): this;
    /**
     * Remove button from group
     */
    unregisterButton(btn: IPluginButton): this;
    /**
     * Prepare toolbar items and append buttons in groups
     */
    private beforeToolbarBuild;
    readonly isJodit: boolean;
    /** @override **/
    protected constructor(options?: Partial<IViewOptions>, isJodit?: boolean);
    destruct(): void;
}
