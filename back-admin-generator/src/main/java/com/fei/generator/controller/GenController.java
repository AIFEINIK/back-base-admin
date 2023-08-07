package com.fei.generator.controller;

import com.fei.common.page.Page;
import com.fei.common.result.Result;
import com.fei.generator.common.Convert;
import com.fei.generator.domain.GenTable;
import com.fei.generator.domain.GenTableColumn;
import com.fei.generator.service.IGenTableColumnService;
import com.fei.generator.service.IGenTableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 */
@RestController
@RequestMapping("/tool/gen" )
public class GenController {
    @Resource
    private IGenTableService genTableServiceImpl;

    @Resource
    private IGenTableColumnService genTableColumnServiceImpl;

    /**
     * 查询代码生成列表
     */
    @PreAuthorize("@ss.auth('tool:gen:list')" )
    @GetMapping("/list" )
    public Result<Page<GenTable>> genList(GenTable genTable) {
        PageHelper.startPage(1, 10);
        List<GenTable> list = genTableServiceImpl.selectGenTableList(genTable);

        Page<GenTable> page = Page.of((int)new PageInfo(list).getTotal(), list);
        return Result.success(page);
    }

    /**
     * 修改代码生成业务
     */
    @PreAuthorize("@ss.auth('tool:gen:query')" )
    @GetMapping(value = "/{tableId}" )
    public Result<Map<String, Object>> getInfo(@PathVariable Long tableId) {
        GenTable table = genTableServiceImpl.selectGenTableById(tableId);
        List<GenTable> tables = genTableServiceImpl.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnServiceImpl.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info" , table);
        map.put("rows" , list);
        map.put("tables" , tables);
        return Result.success(map);
    }

    /**
     * 查询数据库列表
     */
    @PreAuthorize("@ss.auth('tool:gen:list')" )
    @GetMapping("/db/list" )
    public Result<Page<GenTable>> dataList(GenTable genTable) {
        PageHelper.startPage(1, 10);
        List<GenTable> list = genTableServiceImpl.selectDbTableList(genTable);
        Page<GenTable> page = Page.of((int)new PageInfo(list).getTotal(), list);
        return Result.success(page);
    }

    /**
     * 查询数据表字段列表
     */
    @PreAuthorize("@ss.auth('tool:gen:list')" )
    @GetMapping(value = "/column/{tableId}" )
    public Result<Page<GenTableColumn>> columnList(Long tableId) {
        List<GenTableColumn> list = genTableColumnServiceImpl.selectGenTableColumnListByTableId(tableId);
        Page<GenTableColumn> page = Page.of(list.size(), list);
        return Result.success(page);
    }

    /**
     * 导入表结构（保存）
     */
    @PreAuthorize("@ss.auth('tool:gen:import')" )
    @PostMapping("/importTable" )
    public Result importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableServiceImpl.selectDbTableListByNames(tableNames);
        genTableServiceImpl.importGenTable(tableList);
        return new Result();
    }

    /**
     * 修改保存代码生成业务
     */
    @PreAuthorize("@ss.auth('tool:gen:edit')" )
    @PutMapping
    public Result editSave(@Validated @RequestBody GenTable genTable) {
        genTableServiceImpl.validateEdit(genTable);
        genTableServiceImpl.updateGenTable(genTable);
        return new Result();
    }

    /**
     * 删除代码生成
     */
    @PreAuthorize("@ss.auth('tool:gen:remove')" )
    @DeleteMapping("/{tableIds}" )
    public Result remove(@PathVariable Long[] tableIds) {
        genTableServiceImpl.deleteGenTableByIds(tableIds);
        return new Result();
    }

    /**
     * 预览代码
     */
    @PreAuthorize("@ss.auth('tool:gen:preview')" )
    @GetMapping("/preview/{tableId}" )
    public Result preview(@PathVariable("tableId" ) Long tableId) throws IOException {
        Map<String, String> dataMap = genTableServiceImpl.previewCode(tableId);
        return Result.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @PreAuthorize("@ss.auth('tool:gen:code')" )
    @GetMapping("/download/{tableName}" )
    public void download(HttpServletResponse response, @PathVariable("tableName" ) String tableName) throws IOException {
        byte[] data = genTableServiceImpl.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @PreAuthorize("@ss.auth('tool:gen:code')" )
    @GetMapping("/genCode/{tableName}" )
    public Result genCode(@PathVariable("tableName" ) String tableName) {
        genTableServiceImpl.generatorCode(tableName);
        return new Result();
    }

    /**
     * 同步数据库
     */
    @PreAuthorize("@ss.auth('tool:gen:edit')" )
    @GetMapping("/synchDb/{tableName}" )
    public Result synchDb(@PathVariable("tableName" ) String tableName) {
        genTableServiceImpl.synchDb(tableName);
        return new Result();
    }

    /**
     * 批量生成代码
     */
    @PreAuthorize("@ss.auth('tool:gen:code')" )
    @GetMapping("/batchGenCode" )
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableServiceImpl.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin" , "*" );
        response.addHeader("Access-Control-Expose-Headers" , "Content-Disposition" );
        response.setHeader("Content-Disposition" , "attachment; filename=\"ruoyi.zip\"" );
        response.addHeader("Content-Length" , "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8" );
        IOUtils.write(data, response.getOutputStream());
    }
}